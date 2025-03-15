package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.dto.ReconocimientoDTO;
import com.proyecto.SENTIA.service.ReconocimientoService;

@RestController
@RequestMapping("/reconocimientos")
public class ReconocimientoController {
    @Autowired
    private ReconocimientoService reconocimientoService;

    @GetMapping
    public List<ReconocimientoDTO> getAllReconocimientos() {
        return reconocimientoService.findAll();
    }
    

}
