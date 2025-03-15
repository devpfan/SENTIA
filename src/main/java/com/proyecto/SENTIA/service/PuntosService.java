package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.PuntosDTO;
import com.proyecto.SENTIA.model.entity.Puntos;
import com.proyecto.SENTIA.repository.PuntosRepository;

@Service
public class PuntosService {

    @Autowired
    private PuntosRepository puntosRepository;

    public List<PuntosDTO> findAll() {
        return puntosRepository.findAll().stream().map(PuntosDTO::new).collect(Collectors.toList());
    }

    public Optional<PuntosDTO> findById(Long id) {
        return puntosRepository.findById(id).map(PuntosDTO::new);
    }

    public PuntosDTO save(PuntosDTO puntosDTO) {
        Puntos puntos = puntosDTO.toEntity();
        return new PuntosDTO(puntosRepository.save(puntos));
    }

}
