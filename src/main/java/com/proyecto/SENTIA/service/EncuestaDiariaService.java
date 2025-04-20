package com.proyecto.SENTIA.service;

import com.proyecto.SENTIA.model.entity.EncuestaDiaria;
import com.proyecto.SENTIA.model.entity.OpcionRespuesta;
import com.proyecto.SENTIA.model.entity.RespuestaDiaria;
import com.proyecto.SENTIA.repository.EncuestaDiariaRepository;
import com.proyecto.SENTIA.repository.OpcionRespuestaRepository;
import com.proyecto.SENTIA.repository.RespuestaDiariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EncuestaDiariaService {

    @Autowired
    private EncuestaDiariaRepository encuestaDiariaRepository;

    @Autowired
    private OpcionRespuestaRepository opcionRespuestaRepository;

    @Autowired
    private RespuestaDiariaRepository respuestaDiariaRepository;

    public EncuestaDiaria crearEncuestaDiaria(String pregunta, List<String> opciones) {
        EncuestaDiaria encuesta = new EncuestaDiaria();
        encuesta.setPregunta(pregunta);
        encuesta.setFecha(LocalDate.now());
        encuesta.setEstado("I"); // Por defecto, la encuesta es inactiva
        encuesta = encuestaDiariaRepository.save(encuesta);

        for (String opcion : opciones) {
            OpcionRespuesta opcionRespuesta = new OpcionRespuesta();
            opcionRespuesta.setTexto(opcion);
            opcionRespuesta.setEncuestaDiaria(encuesta);
            opcionRespuestaRepository.save(opcionRespuesta);
        }

        return encuesta;
    }

    public List<EncuestaDiaria> obtenerEncuestasDiarias() {
        return encuestaDiariaRepository.findAll();
    }

    public EncuestaDiaria marcarComoActiva(Long id) {
        // Desactivar cualquier encuesta activa
        Optional<EncuestaDiaria> encuestaActiva = encuestaDiariaRepository.findByEstado("A");
        encuestaActiva.ifPresent(encuesta -> {
            encuesta.setEstado("I");
            encuestaDiariaRepository.save(encuesta);
        });

        // Activar la nueva encuesta
        EncuestaDiaria encuesta = encuestaDiariaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));
        encuesta.setEstado("A");
        return encuestaDiariaRepository.save(encuesta);
    }

    public EncuestaDiaria marcarComoInactiva(Long id) {
        EncuestaDiaria encuesta = encuestaDiariaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));
        encuesta.setEstado("I");
        return encuestaDiariaRepository.save(encuesta);
    }

    public void registrarRespuesta(Long encuestaId, String identificacionUsuario) {
        // Validar si la encuesta existe y está activa
        EncuestaDiaria encuesta = encuestaDiariaRepository.findById(encuestaId)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));

        if (!"A".equals(encuesta.getEstado())) {
            throw new IllegalStateException("La encuesta no está activa");
        }

        // Validar si ya respondió la encuesta en el día actual
        LocalDate hoy = LocalDate.now();
        Optional<RespuestaDiaria> respuestaExistente = respuestaDiariaRepository
                .findByEncuestaDiariaIdAndIdentificacionUsuarioAndFechaRespuesta(encuestaId, identificacionUsuario, hoy);

        if (respuestaExistente.isPresent()) {
            throw new IllegalStateException("El usuario ya respondió la encuesta diaria de hoy");
        }

        // Registrar la respuesta
        RespuestaDiaria respuesta = new RespuestaDiaria();
        respuesta.setEncuestaDiaria(encuesta);
        respuesta.setIdentificacionUsuario(identificacionUsuario);
        respuesta.setFechaRespuesta(hoy);
        respuestaDiariaRepository.save(respuesta);
    }

    public boolean verificarSiEncuestaYaRespondida(String identificacionUsuario) {
        // Buscar la encuesta activa
        Optional<EncuestaDiaria> encuestaActiva = encuestaDiariaRepository.findByEstado("A");
        if (encuestaActiva.isEmpty()) {
            throw new IllegalStateException("No hay una encuesta diaria activa");
        }

        // Verificar si el usuario ya respondió la encuesta activa hoy
        LocalDate hoy = LocalDate.now();
        Long encuestaId = encuestaActiva.get().getId();
        return respuestaDiariaRepository
                .findByEncuestaDiariaIdAndIdentificacionUsuarioAndFechaRespuesta(encuestaId, identificacionUsuario, hoy)
                .isPresent();
    }
}