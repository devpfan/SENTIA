package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.Comentario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComentarioDTO {

    private long id;
    private long usuarioId;
    private String texto;
    private String fecha;

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.usuarioId = comentario.getUsuario().getId();
        this.texto = comentario.getTexto();
        this.fecha = comentario.getFecha().toString();
    }

    public Comentario toEntity() {
        Comentario comentario = new Comentario();
        comentario.setId(this.id);
        comentario.setTexto(this.texto);
        return comentario;
    }

}
