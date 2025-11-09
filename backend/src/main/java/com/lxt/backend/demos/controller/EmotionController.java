package com.lxt.backend.demos.controller;

import com.lxt.backend.demos.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * @Author: xintian
 * @CreateTime: 2025-11-09 17:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/emotion")
@CrossOrigin(origins = "http://localhost:5173") // 允许 Vue 前端访问
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    @PostMapping("/analyze")
    public String analyze(@RequestBody Map<String, String> body) {
        String text = body.get("text");
        return emotionService.analyzeEmotion(text);
    }
}
