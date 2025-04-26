package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.proyecto.SENTIA.model.dto.NoticiasDTO;
import com.proyecto.SENTIA.service.NoticiaService;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public List<NoticiasDTO> getAllNoticias() {
        return noticiaService.findAll();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public NoticiasDTO createNoticia(@RequestBody NoticiasDTO noticiaDTO) {
        return noticiaService.save(noticiaDTO);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public NoticiasDTO updateNoticia(@PathVariable Long id, @RequestBody NoticiasDTO noticiaDTO) {
        return noticiaService.update(id, noticiaDTO);
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteNoticia(@PathVariable Long id) {
        noticiaService.delete(id);
    }
}
