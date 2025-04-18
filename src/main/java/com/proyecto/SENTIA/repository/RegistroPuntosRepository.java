package com.proyecto.SENTIA.repository;

import com.proyecto.SENTIA.model.entity.RegistroPuntos;
import com.proyecto.SENTIA.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroPuntosRepository extends JpaRepository<RegistroPuntos, Long> {
    List<RegistroPuntos> findByUsuarioAndFechaBetween(Usuario usuario, LocalDate inicio, LocalDate fin);
}
