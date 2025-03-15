package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.dto.PuntosDTO;
import com.proyecto.SENTIA.service.PuntosService;

@RestController
@RequestMapping("/puntos")
public class PuntosController {

     @Autowired
    private PuntosService puntosService;

    @GetMapping
    public List<PuntosDTO> getAllPuntos() {
        return puntosService.findAll();
    }
}
