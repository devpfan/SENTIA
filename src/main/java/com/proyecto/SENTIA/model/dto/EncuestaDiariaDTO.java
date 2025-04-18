package com.proyecto.SENTIA.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class EncuestaDiariaDTO {

    private String pregunta;
    private List<String> opciones;

}
