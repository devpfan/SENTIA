package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.RespuestaDTO;
import com.proyecto.SENTIA.model.entity.Respuesta;
import com.proyecto.SENTIA.repository.RespuestaRepository;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    public List<RespuestaDTO> findAll() {
        return respuestaRepository.findAll().stream().map(RespuestaDTO::new).collect(Collectors.toList());
    }

    public Optional<RespuestaDTO> findById(Long id) {
        return respuestaRepository.findById(id).map(RespuestaDTO::new);
    }

    public RespuestaDTO save(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = respuestaDTO.toEntity();
        return new RespuestaDTO(respuestaRepository.save(respuesta));
    }

    public void deleteById(Long id) {
        respuestaRepository.deleteById(id);
    }

}
