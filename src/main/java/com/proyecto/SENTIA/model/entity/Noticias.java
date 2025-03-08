package com.proyecto.SENTIA.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "noticias")
public class Noticias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private String titulo;
    private String contenido;
    private LocalDateTime fechaPublicacion = LocalDateTime.now();
}