package com.proyecto.SENTIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.SENTIA.model.entity.EncuestaDiaria;

@Repository
public interface EncuestaDiariaRepository extends JpaRepository<EncuestaDiaria, Long>{

}
