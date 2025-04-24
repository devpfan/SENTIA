package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.AnalisisSentimiento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnalisisSentimientoDTO {

    private Long id;
    private Long feedbackId;
    private Float puntuacionSentimiento;
    private String categoriaSentimiento;

    public AnalisisSentimientoDTO(AnalisisSentimiento analisisSentimiento) {
        this.id = analisisSentimiento.getId();
        this.feedbackId = analisisSentimiento.getFeedback().getId();
        this.puntuacionSentimiento = analisisSentimiento.getPuntuacionSentimiento();
        this.categoriaSentimiento = analisisSentimiento.getCategoriaSentimiento();
    }

    public AnalisisSentimiento toEntity() {
        AnalisisSentimiento analisisSentimiento = new AnalisisSentimiento();
        analisisSentimiento.setId(this.id);
        analisisSentimiento.setPuntuacionSentimiento(this.puntuacionSentimiento);
        analisisSentimiento.setCategoriaSentimiento(this.categoriaSentimiento);
        return analisisSentimiento;
    }
}