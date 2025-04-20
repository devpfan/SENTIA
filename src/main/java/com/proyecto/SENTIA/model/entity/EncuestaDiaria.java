package com.proyecto.SENTIA.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "encuestas_diarias")
public class EncuestaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pregunta; 

    @OneToMany(mappedBy = "encuestaDiaria", cascade = CascadeType.ALL)
    @JsonManagedReference 
    private List<OpcionRespuesta> opciones; 

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 1)
    private String estado;

}
