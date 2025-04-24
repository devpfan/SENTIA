package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.entity.TipoFeedback;
import com.proyecto.SENTIA.repository.TipoFeedbackRepository;

@RestController
@RequestMapping("/tipo-feedback")
public class TipoFeedbackController {

    @Autowired
    private TipoFeedbackRepository tipoFeedbackRepository;

    @GetMapping
    public List<TipoFeedback> getAllTiposFeedback() {
        return tipoFeedbackRepository.findAll();
    }
}