package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.ReconocimientoDTO;
import com.proyecto.SENTIA.model.entity.Reconocimiento;
import com.proyecto.SENTIA.repository.ReconocimientoRepository;

@Service
public class ReconocimientoService {

     @Autowired
    private ReconocimientoRepository reconocimientoRepository;

    public List<ReconocimientoDTO> findAll() {
        return reconocimientoRepository.findAll().stream().map(ReconocimientoDTO::new).collect(Collectors.toList());
    }

    public Optional<ReconocimientoDTO> findById(Long id) {
        return reconocimientoRepository.findById(id).map(ReconocimientoDTO::new);
    }

    public ReconocimientoDTO save(ReconocimientoDTO reconocimientoDTO) {
        Reconocimiento reconocimiento = reconocimientoDTO.toEntity();
        return new ReconocimientoDTO(reconocimientoRepository.save(reconocimiento));
    }

}
