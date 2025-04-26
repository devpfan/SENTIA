package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Base64;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.NoticiasDTO;
import com.proyecto.SENTIA.model.entity.Noticias;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.model.entity.TipoNoticia;
import com.proyecto.SENTIA.repository.NoticiaRepository;
import com.proyecto.SENTIA.repository.UsuarioRepository;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<NoticiasDTO> findAll() {
        return noticiaRepository.findAll().stream().map(NoticiasDTO::new).collect(Collectors.toList());
    }

    public Optional<NoticiasDTO> findById(Long id) {
        return noticiaRepository.findById(id).map(NoticiasDTO::new);
    }

    public NoticiasDTO save(NoticiasDTO noticiaDTO) {
        Noticias noticia = noticiaDTO.toEntity();

        Usuario autor = usuarioRepository.findById(noticiaDTO.getAutorId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        noticia.setAutor(autor);

        noticia.setTipo(TipoNoticia.values()[noticiaDTO.getTipo()]);

        if (noticia.isPublicada()) {
            noticia.setFechaPublicacion(LocalDateTime.now());
        }

        return new NoticiasDTO(noticiaRepository.save(noticia));
    }

    public NoticiasDTO update(Long id, NoticiasDTO noticiaDTO) {
        Noticias noticia = noticiaRepository.findById(id).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
        noticia.setTitulo(noticiaDTO.getTitulo());
        noticia.setContenido(noticiaDTO.getContenido());
        noticia.setTipo(TipoNoticia.values()[noticiaDTO.getTipo()]);
        noticia.setImagen(noticiaDTO.getImagenBase64() != null ? Base64.getDecoder().decode(noticiaDTO.getImagenBase64()) : null);
        noticia.setPublicada(noticiaDTO.isPublicada());
        if (noticia.isPublicada() && noticia.getFechaPublicacion() == null) {
            noticia.setFechaPublicacion(LocalDateTime.now());
        }
        return new NoticiasDTO(noticiaRepository.save(noticia));
    }

    public void delete(Long id) {
        noticiaRepository.deleteById(id);
    }
}
