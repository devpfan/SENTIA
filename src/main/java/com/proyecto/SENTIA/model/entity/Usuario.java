package com.proyecto.SENTIA.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String departamento;
    private String rol;
    private String telefono;
    private String password;
    private String identificacion;
    private String cargo;
    @Column(columnDefinition = "BYTEA")
    private byte[] foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RegistroPuntos> puntos;
    private String estado;
    
}
