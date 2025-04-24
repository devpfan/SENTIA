package com.proyecto.SENTIA.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;
    private String texto;
    private LocalDateTime fecha = LocalDateTime.now(); 
    @ManyToOne
    @JoinColumn(name = "tipo_feedback_id", nullable = false)
    private TipoFeedback tipoFeedback;
    private boolean anonimo;
    
}
