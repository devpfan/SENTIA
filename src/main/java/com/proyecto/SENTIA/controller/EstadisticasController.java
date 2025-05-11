package com.proyecto.SENTIA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.service.UsuarioService;
import com.proyecto.SENTIA.service.EncuestaSemanalService;
import com.proyecto.SENTIA.service.FeedbackService;

import java.util.Map;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EncuestaSemanalService encuestaSemanalService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/sistema")
    public ResponseEntity<Map<String, Long>> obtenerEstadisticasSistema() {
        long totalUsers = usuarioService.countUsuarios();
        long totalSurveys = encuestaSemanalService.countEncuestasCompletadas();
        long totalFeedback = feedbackService.countFeedback();

        return ResponseEntity.ok(Map.of(
            "totalUsers", totalUsers,
            "totalSurveys", totalSurveys,
            "totalFeedback", totalFeedback
        ));
    }
}