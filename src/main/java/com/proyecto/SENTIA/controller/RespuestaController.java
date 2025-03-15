package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.dto.RespuestaDTO;
import com.proyecto.SENTIA.service.RespuestaService;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public List<RespuestaDTO> getAllRespuestas() {
        return respuestaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> getRespuestaById(@PathVariable Long id) {
        return respuestaService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RespuestaDTO createRespuesta(@RequestBody RespuestaDTO respuestaDTO) {
        return respuestaService.save(respuestaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRespuesta(@PathVariable Long id) {
        respuestaService.deleteById(id);
    }
}
