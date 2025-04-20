package com.proyecto.SENTIA.controller;

import com.proyecto.SENTIA.model.dto.EncuestaDiariaDTO;
import com.proyecto.SENTIA.model.entity.EncuestaDiaria;
import com.proyecto.SENTIA.service.EncuestaDiariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/encuestas/diarias")
public class EncuestaDiariaController {

    @Autowired
    private EncuestaDiariaService encuestaDiariaService;

    @PostMapping
    public ResponseEntity<EncuestaDiaria> crearEncuestaDiaria(@RequestBody EncuestaDiariaDTO encuestaDiariaDTO) {
        EncuestaDiaria encuesta = encuestaDiariaService.crearEncuestaDiaria(encuestaDiariaDTO.getPregunta(), encuestaDiariaDTO.getOpciones());
        return ResponseEntity.ok(encuesta);
    }

    @GetMapping
    public ResponseEntity<List<EncuestaDiaria>> obtenerEncuestasDiarias() {
        List<EncuestaDiaria> encuestas = encuestaDiariaService.obtenerEncuestasDiarias();
        return ResponseEntity.ok(encuestas);
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<EncuestaDiaria> activarEncuesta(@PathVariable Long id) {
        EncuestaDiaria encuesta = encuestaDiariaService.marcarComoActiva(id);
        return ResponseEntity.ok(encuesta);
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<EncuestaDiaria> desactivarEncuesta(@PathVariable Long id) {
        EncuestaDiaria encuesta = encuestaDiariaService.marcarComoInactiva(id);
        return ResponseEntity.ok(encuesta);
    }

    @PostMapping("/{id}/responder")
    public ResponseEntity<String> responderEncuesta(
            @PathVariable Long id,
            @RequestParam String identificacionUsuario) {
        encuestaDiariaService.registrarRespuesta(id, identificacionUsuario);
        return ResponseEntity.ok("Respuesta registrada correctamente");
    }

    @GetMapping("/verificar-respuesta")
    public ResponseEntity<Map<String, Boolean>> verificarRespuesta(
            @RequestParam String identificacionUsuario) {
        boolean yaRespondida = encuestaDiariaService.verificarSiEncuestaYaRespondida(identificacionUsuario);
        return ResponseEntity.ok(Map.of("yaRespondida", yaRespondida));
    }
}