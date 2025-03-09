package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.Encuesta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncuestaDTO {

    private Long id;
    private String preguntaTexto;
    private String fechaCreacion;
        
    public EncuestaDTO(Encuesta encuesta){
        this.id = encuesta.getId();
        this.preguntaTexto = encuesta.getPreguntaTexto();
        this.fechaCreacion = encuesta.getFechaCreacion().toString();
    }

    public Encuesta toEntity() {
        Encuesta encuesta = new Encuesta();
        encuesta.setId(this.id);
        encuesta.setPreguntaTexto(this.preguntaTexto);
        return encuesta;
    }

}
