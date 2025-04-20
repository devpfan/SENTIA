package com.proyecto.SENTIA.repository;

import com.proyecto.SENTIA.model.entity.RespuestaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RespuestaSemanalRepository extends JpaRepository<RespuestaSemanal, Long> {
    Optional<RespuestaSemanal> findByEncuestaSemanalIdAndIdentificacionUsuarioAndFechaRespuesta(
            Long encuestaSemanalId, String identificacionUsuario, LocalDate fechaRespuesta);
}