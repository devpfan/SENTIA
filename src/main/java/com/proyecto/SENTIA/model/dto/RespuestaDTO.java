package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.Respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RespuestaDTO {

    private Long id;
    private UsuarioDTO usuario;
    private EncuestaDTO encuesta;
    private String respuesta;
    private String fechaRespuesta;

    public RespuestaDTO(Respuesta respuesta) {
        this.id = respuesta.getId();
        this.usuario = new UsuarioDTO(respuesta.getUsuario());
        this.encuesta = new EncuestaDTO(respuesta.getEncuesta());
        this.respuesta = respuesta.getRespuesta();
        this.fechaRespuesta = respuesta.getFechaRespuesta().toString();
    }

    public Respuesta toEntity() {

        Respuesta respuesta = new Respuesta();
        respuesta.setId(this.id);
        respuesta.setUsuario(this.usuario.toEntity());
        respuesta.setEncuesta(this.encuesta.toEntity());
        respuesta.setRespuesta(this.respuesta);
        return respuesta;   
       
    }




}
