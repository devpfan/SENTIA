package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.entity.AnalisisFeedback;
import com.proyecto.SENTIA.repository.AnalisisFeedbackRepository;

@Service
public class AnalisisFeedbackService {

    @Autowired
    private AnalisisFeedbackRepository analisisFeedbackRepository;


    public List<AnalisisFeedback> findAll() {
        return analisisFeedbackRepository.findAll();
    }

    public Optional<AnalisisFeedback> findById(Long id) {
        return analisisFeedbackRepository.findById(id);
    }
}