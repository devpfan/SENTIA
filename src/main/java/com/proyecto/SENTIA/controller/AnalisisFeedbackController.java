package com.proyecto.SENTIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.entity.AnalisisFeedback;
import com.proyecto.SENTIA.service.AnalisisFeedbackService;

@RestController
@RequestMapping("/analisis-feedback")
public class AnalisisFeedbackController {

    @Autowired
    private AnalisisFeedbackService analisisFeedbackService;

    @GetMapping
    public List<AnalisisFeedback> getAllAnalisisFeedback() {
        return analisisFeedbackService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalisisFeedback> getAnalisisFeedbackById(@PathVariable Long id) {
        return analisisFeedbackService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}