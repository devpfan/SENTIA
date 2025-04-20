package com.proyecto.SENTIA.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "opciones_respuesta")
public class OpcionRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "encuesta_diaria_id")
    @JsonBackReference
    private EncuestaDiaria encuestaDiaria;

    @ManyToOne
    @JoinColumn(name = "encuesta_semanal_id")
    @JsonBackReference
    private EncuestaSemanal encuestaSemanal;
}
