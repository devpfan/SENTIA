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

import com.proyecto.SENTIA.model.dto.EncuestaDTO;
import com.proyecto.SENTIA.service.EncuestaService;

@RestController
@RequestMapping("/encuestas")
public class EncuestaController {
    @Autowired
    private EncuestaService encuestaService;

    @GetMapping
    public List<EncuestaDTO> getAllEncuestas() {
        return encuestaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncuestaDTO> getEncuestaById(@PathVariable Long id) {
        return encuestaService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EncuestaDTO createEncuesta(@RequestBody EncuestaDTO encuestaDTO) {
        return encuestaService.save(encuestaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEncuesta(@PathVariable Long id) {
        encuestaService.deleteById(id);
    }

}
