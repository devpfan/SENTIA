package com.proyecto.SENTIA.service;

import com.proyecto.SENTIA.model.entity.RegistroPuntos;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.repository.RegistroPuntosRepository;
import com.proyecto.SENTIA.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class RegistroPuntosService {

    @Autowired
    private RegistroPuntosRepository registroPuntosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarPuntos(Long usuarioId, int puntos, String tipoEncuesta) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        RegistroPuntos registro = new RegistroPuntos();
        registro.setUsuario(usuario);
        registro.setPuntos(puntos);
        registro.setFecha(LocalDate.now());
        registro.setTipoEncuesta(tipoEncuesta);

        registroPuntosRepository.save(registro);
    }

    public int calcularPuntosSemanales(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Obtener el rango de fechas de la semana actual
        LocalDate inicioSemana = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate finSemana = LocalDate.now().with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));

        // Obtener los registros de puntos de la semana actual
        List<RegistroPuntos> registrosSemana = registroPuntosRepository.findByUsuarioAndFechaBetween(usuario, inicioSemana, finSemana);

        // Sumar los puntos
        int puntosTotales = registrosSemana.stream()
                .mapToInt(RegistroPuntos::getPuntos)
                .sum();

        return puntosTotales;
    }

    public int calcularPuntosSemanalesConBonus(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Obtener el rango de fechas de la semana actual
        LocalDate inicioSemana = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate finSemana = LocalDate.now().with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));

        // Obtener los registros de puntos de la semana actual
        List<RegistroPuntos> registrosSemana = registroPuntosRepository.findByUsuarioAndFechaBetween(usuario, inicioSemana, finSemana);

        // Sumar los puntos
        int puntosTotales = registrosSemana.stream()
                .mapToInt(RegistroPuntos::getPuntos)
                .sum();

        // Verificar si respondió todas las encuestas diarias (5 días laborales)
        long encuestasDiariasRespondidas = registrosSemana.stream()
                .filter(registro -> "diaria".equalsIgnoreCase(registro.getTipoEncuesta()))
                .count();

        if (encuestasDiariasRespondidas >= 5) {
            puntosTotales += 5; // Bonus por responder todas las encuestas diarias
        }

        return puntosTotales;
    }
}