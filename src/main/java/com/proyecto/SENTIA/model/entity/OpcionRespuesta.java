package com.proyecto.SENTIA.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "opciones_respuesta")
public class OpcionRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto; // Texto de la opción de respuesta

    @ManyToOne
    @JoinColumn(name = "encuesta_diaria_id")
    private EncuestaDiaria encuestaDiaria;

    @ManyToOne
    @JoinColumn(name = "encuesta_semanal_id")
    private EncuestaSemanal encuestaSemanal;

}
