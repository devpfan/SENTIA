package com.proyecto.SENTIA.controller;

import com.proyecto.SENTIA.model.dto.EncuestaDiariaDTO;
import com.proyecto.SENTIA.model.entity.EncuestaDiaria;
import com.proyecto.SENTIA.service.EncuestaDiariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encuestas/diarias")
public class EncuestaDiariaController {

    @Autowired
    private EncuestaDiariaService encuestaDiariaService;

    @PostMapping
    public ResponseEntity<EncuestaDiaria> crearEncuestaDiaria(@RequestBody EncuestaDiariaDTO encuestaDiariaDTO) {
        EncuestaDiaria encuesta = encuestaDiariaService.crearEncuestaDiaria(encuestaDiariaDTO.getPregunta(), encuestaDiariaDTO.getOpciones());
        return ResponseEntity.ok(encuesta);
    }

    @GetMapping
    public ResponseEntity<List<EncuestaDiaria>> obtenerEncuestasDiarias() {
        List<EncuestaDiaria> encuestas = encuestaDiariaService.obtenerEncuestasDiarias();
        return ResponseEntity.ok(encuestas);
    }
}