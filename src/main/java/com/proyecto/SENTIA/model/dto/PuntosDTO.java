package com.proyecto.SENTIA.model.dto;

import com.proyecto.SENTIA.model.entity.Puntos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PuntosDTO {

    private Long id;
    private Long usuarioId;
    private Integer puntosObtenidos;
    private Integer puntosCanjeados;

    public PuntosDTO(Puntos puntos) {
        this.id = puntos.getId();
        this.usuarioId = puntos.getUsuario().getId();
        this.puntosObtenidos = puntos.getPuntosObtenidos();
        this.puntosCanjeados = puntos.getPuntosCanjeados();
    }

    public Puntos toEntity() {
        Puntos puntos = new Puntos();
        puntos.setId(this.id);
        puntos.setPuntosObtenidos(this.puntosObtenidos);
        puntos.setPuntosCanjeados(this.puntosCanjeados);
        return puntos;
    }
}