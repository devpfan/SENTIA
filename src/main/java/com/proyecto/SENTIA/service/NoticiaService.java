package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.NoticiasDTO;
import com.proyecto.SENTIA.model.entity.Noticias;
import com.proyecto.SENTIA.repository.NoticiaRepository;

@Service
public class NoticiaService {

     @Autowired
    private NoticiaRepository noticiaRepository;

    public List<NoticiasDTO> findAll() {
        return noticiaRepository.findAll().stream().map(NoticiasDTO::new).collect(Collectors.toList());
    }

    public Optional<NoticiasDTO> findById(Long id) {
        return noticiaRepository.findById(id).map(NoticiasDTO::new);
    }

    public NoticiasDTO save(NoticiasDTO noticiaDTO) {
        Noticias noticia = noticiaDTO.toEntity();
        return new NoticiasDTO(noticiaRepository.save(noticia));
    }

}
