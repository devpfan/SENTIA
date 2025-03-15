package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.Reconocimiento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReconocimientoDTO {

    private Long id;
    private Long emisorId;
    private Long receptorId;
    private String mensaje;

    public ReconocimientoDTO(Reconocimiento reconocimiento) {
        this.id = reconocimiento.getId();
        this.emisorId = reconocimiento.getEmisor().getId();
        this.receptorId = reconocimiento.getReceptor().getId();
        this.mensaje = reconocimiento.getMensaje();
    }

    public Reconocimiento toEntity() {
        Reconocimiento reconocimiento = new Reconocimiento();
        reconocimiento.setId(this.id);
        reconocimiento.setMensaje(this.mensaje);
        return reconocimiento;
    }
}