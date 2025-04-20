package com.proyecto.SENTIA.controller;

import com.proyecto.SENTIA.model.dto.EncuestaSemanalDTO;
import com.proyecto.SENTIA.model.entity.EncuestaSemanal;
import com.proyecto.SENTIA.service.EncuestaSemanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/encuestas/semanales")
public class EncuestaSemanalController {

    @Autowired
    private EncuestaSemanalService encuestaSemanalService;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @PostMapping
    public ResponseEntity<EncuestaSemanal> crearEncuestaSemanal(@RequestBody EncuestaSemanalDTO encuestaSemanalDTO) {
        EncuestaSemanal encuesta = encuestaSemanalService.crearEncuestaSemanal(
                encuestaSemanalDTO.getPreguntas(),
                encuestaSemanalDTO.getOpciones(),
                LocalDate.parse(encuestaSemanalDTO.getFechaInicio()),
                LocalDate.parse(encuestaSemanalDTO.getFechaFin())
        );
        return ResponseEntity.ok(encuesta);
    }

    @GetMapping
    public ResponseEntity<List<EncuestaSemanal>> obtenerEncuestasSemanales() {
        List<EncuestaSemanal> encuestas = encuestaSemanalService.obtenerEncuestasSemanales();
        return ResponseEntity.ok(encuestas);
    }

    @GetMapping("/verificar-respuesta")
    public ResponseEntity<Map<String, Boolean>> verificarRespuesta(
            @RequestParam String identificacionUsuario) {
        boolean yaRespondida = encuestaSemanalService.verificarSiEncuestaYaRespondida(identificacionUsuario);
        return ResponseEntity.ok(Map.of("yaRespondida", yaRespondida));
    }

    @PostMapping("/{id}/responder")
    public ResponseEntity<String> responderEncuesta(
            @PathVariable Long id,
            @RequestParam String identificacionUsuario) {
        encuestaSemanalService.registrarRespuesta(id, identificacionUsuario);
        return ResponseEntity.ok("Respuesta registrada correctamente");
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<EncuestaSemanal> activarEncuesta(@PathVariable Long id) {
        EncuestaSemanal encuesta = encuestaSemanalService.marcarComoActiva(id);
        return ResponseEntity.ok(encuesta);
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<EncuestaSemanal> desactivarEncuesta(@PathVariable Long id) {
        EncuestaSemanal encuesta = encuestaSemanalService.marcarComoInactiva(id);
        return ResponseEntity.ok(encuesta);
    }
}