package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.AnalisisSentimientoDTO;
import com.proyecto.SENTIA.model.entity.AnalisisSentimiento;
import com.proyecto.SENTIA.repository.AnalisisSentimientoRepository;

@Service
public class AnalisisSentimientoService {

    @Autowired
    private AnalisisSentimientoRepository analisisSentimientoRepository;

    public List<AnalisisSentimientoDTO> findAll() {
        return analisisSentimientoRepository.findAll().stream().map(AnalisisSentimientoDTO::new).collect(Collectors.toList());
    }

    public Optional<AnalisisSentimientoDTO> findById(Long id) {
        return analisisSentimientoRepository.findById(id).map(AnalisisSentimientoDTO::new);
    }

    public AnalisisSentimientoDTO save(AnalisisSentimientoDTO analisisSentimientoDTO) {
        AnalisisSentimiento analisisSentimiento = analisisSentimientoDTO.toEntity();
        return new AnalisisSentimientoDTO(analisisSentimientoRepository.save(analisisSentimiento));
    }
}
