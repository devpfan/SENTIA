package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.dto.AnalisisSentimientoDTO;
import com.proyecto.SENTIA.service.AnalisisSentimientoService;

@RestController
@RequestMapping("/analisis-sentimiento")
public class AnalisisSentimientoController {
     @Autowired
    private AnalisisSentimientoService analisisSentimientoService;

    @GetMapping
    public List<AnalisisSentimientoDTO> getAllAnalisis() {
        return analisisSentimientoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalisisSentimientoDTO> getAnalisisById(@PathVariable Long id) {
        return analisisSentimientoService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
