package com.proyecto.SENTIA.model.entity;

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
@Table(name = "reconocimientos")
public class Reconocimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private Usuario emisor;
    
    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;
    
    private String mensaje;
}