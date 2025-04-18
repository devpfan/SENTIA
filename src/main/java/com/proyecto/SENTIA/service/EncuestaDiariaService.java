package com.proyecto.SENTIA.service;

import com.proyecto.SENTIA.model.entity.EncuestaDiaria;
import com.proyecto.SENTIA.model.entity.OpcionRespuesta;
import com.proyecto.SENTIA.repository.EncuestaDiariaRepository;
import com.proyecto.SENTIA.repository.OpcionRespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EncuestaDiariaService {

    @Autowired
    private EncuestaDiariaRepository encuestaDiariaRepository;

    @Autowired
    private OpcionRespuestaRepository opcionRespuestaRepository;

    public EncuestaDiaria crearEncuestaDiaria(String pregunta, List<String> opciones) {
        EncuestaDiaria encuesta = new EncuestaDiaria();
        encuesta.setPregunta(pregunta);
        encuesta.setFecha(LocalDate.now());
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
}