package com.proyecto.SENTIA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.SENTIA.model.entity.EncuestaDiaria;

@Repository
public interface EncuestaDiariaRepository extends JpaRepository<EncuestaDiaria, Long>{

    Optional<EncuestaDiaria> findByEstado(String estado);

}
