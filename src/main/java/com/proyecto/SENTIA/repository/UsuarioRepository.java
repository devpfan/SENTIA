package com.proyecto.SENTIA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.SENTIA.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByIdentificacion(String identificacion);

}
