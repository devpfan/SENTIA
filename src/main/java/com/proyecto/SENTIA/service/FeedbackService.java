package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.FeedbackDTO;
import com.proyecto.SENTIA.model.entity.AnalisisFeedback;
import com.proyecto.SENTIA.model.entity.Feedback;
import com.proyecto.SENTIA.model.entity.TipoFeedback;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.repository.AnalisisFeedbackRepository;
import com.proyecto.SENTIA.repository.FeedbackRepository;
import com.proyecto.SENTIA.repository.TipoFeedbackRepository;
import com.proyecto.SENTIA.repository.UsuarioRepository;
import com.proyecto.SENTIA.util.HttpClient;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoFeedbackRepository tipoFeedbackRepository;

    @Autowired
    private AnalisisFeedbackRepository analisisFeedbackRepository;

    @Autowired
    private HttpClient httpClient;

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

    public FeedbackDTO saveAndAnalyze(FeedbackDTO feedbackDTO) {
        // Guardar el feedback
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

        Feedback savedFeedback = feedbackRepository.save(feedback);

        // Llamar al microservicio para el análisis de sentimiento
        Map<String, Object> sentimentResult = httpClient.analyzeSentiment(savedFeedback.getId(), savedFeedback.getTexto());

        // Guardar el resultado del análisis en la base de datos
        AnalisisFeedback analisis = new AnalisisFeedback();
        analisis.setFeedback(savedFeedback);
        analisis.setResultado((String) sentimentResult.get("resultado")); // "NEG" o "POS"
        analisisFeedbackRepository.save(analisis);

        return new FeedbackDTO(savedFeedback);
    }

    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }
}
