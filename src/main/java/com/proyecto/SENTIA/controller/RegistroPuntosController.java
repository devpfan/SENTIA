package com.proyecto.SENTIA.controller;

import com.proyecto.SENTIA.service.RegistroPuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/puntos")
public class RegistroPuntosController {

    @Autowired
    private RegistroPuntosService registroPuntosService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<String> registrarPuntos(
            @PathVariable Long usuarioId,
            @RequestParam int puntos,
            @RequestParam String tipoEncuesta
    ) {
        registroPuntosService.registrarPuntos(usuarioId, puntos, tipoEncuesta);
        return ResponseEntity.ok("Puntos registrados correctamente");
    }

    @GetMapping("/semanales/{usuarioId}")
    public ResponseEntity<Map<String, Integer>> obtenerPuntosSemanales(@PathVariable Long usuarioId) {
        int puntosSemanales = registroPuntosService.calcularPuntosSemanalesConBonus(usuarioId);
        return ResponseEntity.ok(Map.of("puntosSemanales", puntosSemanales));
    }
}