package com.proyecto.SENTIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.SENTIA.model.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
