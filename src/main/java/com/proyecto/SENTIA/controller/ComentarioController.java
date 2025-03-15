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

import com.proyecto.SENTIA.model.dto.ComentarioDTO;
import com.proyecto.SENTIA.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<ComentarioDTO> getAllComentarios() {
        return comentarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        return comentarioService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ComentarioDTO createComentario(@RequestBody ComentarioDTO comentarioDTO) {
        return comentarioService.save(comentarioDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComentario(@PathVariable Long id) {
        comentarioService.deleteById(id);
    }


}
