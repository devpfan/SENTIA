package com.proyecto.SENTIA.repository;

import com.proyecto.SENTIA.model.entity.RespuestaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RespuestaDiariaRepository extends JpaRepository<RespuestaDiaria, Long> {
    Optional<RespuestaDiaria> findByEncuestaDiariaIdAndIdentificacionUsuarioAndFechaRespuesta(
            Long encuestaDiariaId, String identificacionUsuario, LocalDate fechaRespuesta);
}