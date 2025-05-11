package com.proyecto.SENTIA.service;

import com.proyecto.SENTIA.model.entity.EncuestaSemanal;
import com.proyecto.SENTIA.model.entity.OpcionRespuesta;
import com.proyecto.SENTIA.model.entity.RespuestaSemanal;
import com.proyecto.SENTIA.repository.EncuestaSemanalRepository;
import com.proyecto.SENTIA.repository.OpcionRespuestaRepository;
import com.proyecto.SENTIA.repository.RespuestaSemanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EncuestaSemanalService {

    @Autowired
    private EncuestaSemanalRepository encuestaSemanalRepository;

    @Autowired
    private OpcionRespuestaRepository opcionRespuestaRepository;

    @Autowired
    private RespuestaSemanalRepository respuestaSemanalRepository;

    public EncuestaSemanal crearEncuestaSemanal(List<String> preguntas, List<String> opciones, LocalDate fechaInicio, LocalDate fechaFin) {
        EncuestaSemanal encuesta = new EncuestaSemanal();
        encuesta.setPreguntas(preguntas);
        encuesta.setFechaInicio(fechaInicio);
        encuesta.setFechaFin(fechaFin);
        encuesta.setEstado("I");
        encuesta = encuestaSemanalRepository.save(encuesta);

        for (String opcion : opciones) {
            OpcionRespuesta opcionRespuesta = new OpcionRespuesta();
            opcionRespuesta.setTexto(opcion);
            opcionRespuesta.setEncuestaSemanal(encuesta);
            opcionRespuestaRepository.save(opcionRespuesta);
        }

        return encuesta;
    }

    public List<EncuestaSemanal> obtenerEncuestasSemanales() {
        return encuestaSemanalRepository.findAll();
    }

    public boolean verificarSiEncuestaYaRespondida(String identificacionUsuario) {
        // Buscar la encuesta semanal activa
        LocalDate hoy = LocalDate.now();
        Optional<EncuestaSemanal> encuestaActiva = encuestaSemanalRepository.findByEstado("A");

        if (encuestaActiva.isPresent()) {
            EncuestaSemanal encuesta = encuestaActiva.get();

            // Verificar si la encuesta activa está fuera del rango de fechas
            if (hoy.isBefore(encuesta.getFechaInicio()) || hoy.isAfter(encuesta.getFechaFin())) {
                // Desactivar la encuesta si ya no está en el rango de fechas
                encuesta.setEstado("I");
                encuestaSemanalRepository.save(encuesta);
                throw new IllegalStateException("La encuesta activa ya no está dentro del rango de fechas");
            }
        } else {
            throw new IllegalStateException("No hay una encuesta semanal activa");
        }

        // Verificar si el usuario ya respondió la encuesta activa
        Long encuestaId = encuestaActiva.get().getId();
        return respuestaSemanalRepository
                .findByEncuestaSemanalIdAndIdentificacionUsuarioAndFechaRespuesta(encuestaId, identificacionUsuario, hoy)
                .isPresent();
    }

    public void registrarRespuesta(Long encuestaId, String identificacionUsuario) {
        // Validar si la encuesta existe y está activa
        EncuestaSemanal encuesta = encuestaSemanalRepository.findById(encuestaId)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));

        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(encuesta.getFechaInicio()) || hoy.isAfter(encuesta.getFechaFin())) {
            throw new IllegalStateException("La encuesta no está activa esta semana");
        }

        // Validar si ya respondió la encuesta esta semana
        if (verificarSiEncuestaYaRespondida(identificacionUsuario)) {
            throw new IllegalStateException("El usuario ya respondió la encuesta semanal de esta semana");
        }

        // Registrar la respuesta
        RespuestaSemanal respuesta = new RespuestaSemanal();
        respuesta.setEncuestaSemanal(encuesta);
        respuesta.setIdentificacionUsuario(identificacionUsuario);
        respuesta.setFechaRespuesta(hoy);
        respuestaSemanalRepository.save(respuesta);
    }

    public EncuestaSemanal marcarComoActiva(Long id) {
        // Buscar la encuesta a activar
        EncuestaSemanal encuesta = encuestaSemanalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));

        LocalDate hoy = LocalDate.now();

        // Verificar si la encuesta está dentro del rango de fechas
        if (hoy.isBefore(encuesta.getFechaInicio()) || hoy.isAfter(encuesta.getFechaFin())) {
            throw new IllegalStateException("No se puede activar una encuesta fuera del rango de fechas actual");
        }

        // Desactivar cualquier encuesta activa
        Optional<EncuestaSemanal> encuestaActiva = encuestaSemanalRepository.findByEstado("A");
        encuestaActiva.ifPresent(e -> {
            e.setEstado("I");
            encuestaSemanalRepository.save(e);
        });

        // Activar la nueva encuesta
        encuesta.setEstado("A");
        return encuestaSemanalRepository.save(encuesta);
    }

    public EncuestaSemanal marcarComoInactiva(Long id) {
        EncuestaSemanal encuesta = encuestaSemanalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));
        encuesta.setEstado("I");
        return encuestaSemanalRepository.save(encuesta);
    }

    public long countEncuestasCompletadas() {
        return respuestaSemanalRepository.count();
    }
}