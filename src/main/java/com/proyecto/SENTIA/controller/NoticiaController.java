package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
