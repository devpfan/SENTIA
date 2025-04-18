package com.proyecto.SENTIA.controller;

import com.proyecto.SENTIA.model.dto.EncuestaSemanalDTO;
import com.proyecto.SENTIA.model.entity.EncuestaSemanal;
import com.proyecto.SENTIA.service.EncuestaSemanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/encuestas/semanales")
public class EncuestaSemanalController {

    @Autowired
    private EncuestaSemanalService encuestaSemanalService;

    @PostMapping
    public ResponseEntity<EncuestaSemanal> crearEncuestaSemanal(@RequestBody EncuestaSemanalDTO encuestaSemanalDTO) {
        EncuestaSemanal encuesta = encuestaSemanalService.crearEncuestaSemanal(
                encuestaSemanalDTO.getPreguntas(),
                encuestaSemanalDTO.getOpciones(),
                LocalDate.parse(encuestaSemanalDTO.getFechaInicio()),
                LocalDate.parse(encuestaSemanalDTO.getFechaFin())
        );
        return ResponseEntity.ok(encuesta);
    }

    @GetMapping
    public ResponseEntity<List<EncuestaSemanal>> obtenerEncuestasSemanales() {
        List<EncuestaSemanal> encuestas = encuestaSemanalService.obtenerEncuestasSemanales();
        return ResponseEntity.ok(encuestas);
    }
}