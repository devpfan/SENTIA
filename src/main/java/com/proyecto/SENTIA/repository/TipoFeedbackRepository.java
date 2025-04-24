package com.proyecto.SENTIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.SENTIA.model.entity.TipoFeedback;

public interface TipoFeedbackRepository extends JpaRepository<TipoFeedback, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
