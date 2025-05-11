package com.proyecto.SENTIA.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> analyzeSentiment(Long feedbackId, String texto) {
        String url = "http://localhost:5000/analizar"; // URL del microservicio
        Map<String, Object> request = new HashMap<>();
        request.put("id", feedbackId);
        request.put("texto", texto);

        return restTemplate.postForObject(url, request, Map.class);
    }
}