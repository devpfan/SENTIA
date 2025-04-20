package com.proyecto.SENTIA.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "encuestas_semanales")
public class EncuestaSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "preguntas_semanales", joinColumns = @JoinColumn(name = "encuesta_id"))
    @Column(name = "pregunta")
    private List<String> preguntas;

    @OneToMany(mappedBy = "encuestaSemanal", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OpcionRespuesta> opciones;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false, length = 1)
    private String estado; // "A" para activa, "I" para inactiva
}
