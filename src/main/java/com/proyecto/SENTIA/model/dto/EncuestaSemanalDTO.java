package com.proyecto.SENTIA.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class EncuestaSemanalDTO {

    private List<String> preguntas; 
    private List<String> opciones; 
    private String fechaInicio;
    private String fechaFin;

}
