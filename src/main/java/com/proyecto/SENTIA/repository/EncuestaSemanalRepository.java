package com.proyecto.SENTIA.repository;

import com.proyecto.SENTIA.model.entity.EncuestaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EncuestaSemanalRepository extends JpaRepository<EncuestaSemanal, Long> {

    // Buscar la encuesta semanal activa dentro del rango de fechas
    Optional<EncuestaSemanal> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin);

    // Buscar la encuesta semanal activa
    Optional<EncuestaSemanal> findByEstado(String estado);

    // Buscar la encuesta semanal activa dentro del rango de fechas y por estado
    Optional<EncuestaSemanal> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqualAndEstado(
            LocalDate fechaInicio, LocalDate fechaFin, String estado);
}
