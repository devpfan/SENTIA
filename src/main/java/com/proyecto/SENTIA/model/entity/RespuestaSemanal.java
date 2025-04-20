package com.proyecto.SENTIA.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "respuestas_semanales")
public class RespuestaSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "encuesta_semanal_id", nullable = false)
    private EncuestaSemanal encuestaSemanal;

    @Column(nullable = false)
    private LocalDate fechaRespuesta;

    @Column(nullable = false)
    private String identificacionUsuario; // Identificación del usuario que respondió
}