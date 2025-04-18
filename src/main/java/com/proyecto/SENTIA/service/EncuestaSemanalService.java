package com.proyecto.SENTIA.service;

import com.proyecto.SENTIA.model.entity.EncuestaSemanal;
import com.proyecto.SENTIA.model.entity.OpcionRespuesta;
import com.proyecto.SENTIA.repository.EncuestaSemanalRepository;
import com.proyecto.SENTIA.repository.OpcionRespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EncuestaSemanalService {

    @Autowired
    private EncuestaSemanalRepository encuestaSemanalRepository;

    @Autowired
    private OpcionRespuestaRepository opcionRespuestaRepository;

    public EncuestaSemanal crearEncuestaSemanal(List<String> preguntas, List<String> opciones, LocalDate fechaInicio, LocalDate fechaFin) {
        EncuestaSemanal encuesta = new EncuestaSemanal();
        encuesta.setPreguntas(preguntas);
        encuesta.setFechaInicio(fechaInicio);
        encuesta.setFechaFin(fechaFin);
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
}