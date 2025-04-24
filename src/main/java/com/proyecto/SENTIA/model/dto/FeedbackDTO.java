package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class FeedbackDTO {

    private Long id;
    private Long usuarioId;
    private String texto;
    private String fecha;
    private Long tipoId;
    private boolean anonimo;

    public FeedbackDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.usuarioId = feedback.getUsuario() != null ? feedback.getUsuario().getId() : null;
        this.texto = feedback.getTexto();
        this.fecha = feedback.getFecha().toString();
        this.tipoId = feedback.getTipoFeedback().getId();
        this.anonimo = feedback.isAnonimo();
    }

    public Feedback toEntity() {
        Feedback feedback = new Feedback();
        feedback.setId(this.id);
        feedback.setTexto(this.texto);
        feedback.setAnonimo(this.anonimo);
        return feedback;
    }
}
