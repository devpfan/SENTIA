package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.ComentarioDTO;
import com.proyecto.SENTIA.model.entity.Comentario;
import com.proyecto.SENTIA.repository.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<ComentarioDTO> findAll() {
        return comentarioRepository.findAll().stream().map(ComentarioDTO::new).collect(Collectors.toList());
    }

    public Optional<ComentarioDTO> findById(Long id) {
        return comentarioRepository.findById(id).map(ComentarioDTO::new);
    }

    public ComentarioDTO save(ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioDTO.toEntity();
        return new ComentarioDTO(comentarioRepository.save(comentario));
    }

    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }

}
