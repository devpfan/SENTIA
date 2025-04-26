package com.proyecto.SENTIA.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
    @Column(columnDefinition = "TEXT")
    private String contenido;
    @Enumerated(EnumType.ORDINAL)
    private TipoNoticia tipo;
    @Lob
    private byte[] imagen; 
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor; 
    private LocalDateTime fechaEscritura = LocalDateTime.now();
    private LocalDateTime fechaPublicacion;
    private boolean publicada = false;
}