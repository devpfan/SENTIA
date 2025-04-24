package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.FeedbackDTO;
import com.proyecto.SENTIA.model.entity.Feedback;
import com.proyecto.SENTIA.model.entity.TipoFeedback;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.repository.FeedbackRepository;
import com.proyecto.SENTIA.repository.TipoFeedbackRepository;
import com.proyecto.SENTIA.repository.UsuarioRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoFeedbackRepository tipoFeedbackRepository;

    public List<FeedbackDTO> findAll() {
        return feedbackRepository.findAll().stream().map(FeedbackDTO::new).collect(Collectors.toList());
    }

    public Optional<FeedbackDTO> findById(Long id) {
        return feedbackRepository.findById(id).map(FeedbackDTO::new);
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackDTO.toEntity();

        if (!feedbackDTO.isAnonimo() && feedbackDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(feedbackDTO.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            feedback.setUsuario(usuario);
        } else {
            feedback.setUsuario(null); 
        }

        TipoFeedback tipo = tipoFeedbackRepository.findById(feedbackDTO.getTipoId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de feedback no encontrado"));
        feedback.setTipoFeedback(tipo);

        return new FeedbackDTO(feedbackRepository.save(feedback));
    }

    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }
}
