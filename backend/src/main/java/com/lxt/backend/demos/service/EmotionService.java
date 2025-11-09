package com.lxt.backend.demos.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author: xintian
 * @CreateTime: 2025-11-09 17:33
 * @Version: 1.0
 */
@Service
public class EmotionService {

    @Value("${flask.url}")
    private String flaskUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeEmotion(String text) {
        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, request, String.class);
        return response.getBody();
    }
}