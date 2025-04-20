package com.proyecto.SENTIA.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "respuestas_diarias")
public class RespuestaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "encuesta_diaria_id", nullable = false)
    private EncuestaDiaria encuestaDiaria;

    @Column(nullable = false)
    private LocalDate fechaRespuesta;

    @Column(nullable = false)
    private String identificacionUsuario; 
}