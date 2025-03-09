package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.EncuestaDTO;
import com.proyecto.SENTIA.model.entity.Encuesta;
import com.proyecto.SENTIA.repository.EncuestaRepository;

@Service
public class EncuestaService {

    @Autowired
    private EncuestaRepository encuestaRepository;

    public List<EncuestaDTO> findAll() {
        return encuestaRepository.findAll().stream().map(EncuestaDTO::new).collect(Collectors.toList());
    }

    public Optional<EncuestaDTO> findById(Long id) {
        return encuestaRepository.findById(id).map(EncuestaDTO::new);
    }

    public EncuestaDTO save(EncuestaDTO encuestaDTO) {
        Encuesta encuesta = encuestaDTO.toEntity();
        return new EncuestaDTO(encuestaRepository.save(encuesta));
    }

    public void deleteById(Long id) {
        encuestaRepository.deleteById(id);
    }
}
