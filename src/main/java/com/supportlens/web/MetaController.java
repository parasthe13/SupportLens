package com.supportlens.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * A tiny "is it alive and what does it do" endpoint. It also doubles as a living
 * status board for the phased roadmap, so anyone hitting {@code GET /api} can see
 * what is built and what is stubbed.
 */
@RestController
@RequestMapping("/api")
public class MetaController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public Map<String, Object> info() {
        return Map.of(
                "service", appName,
                "description", "AI-powered support knowledge assistant (RAG over your documentation)",
                "docs", "/swagger-ui.html",
                "health", "/actuator/health",
                "roadmap", List.of(
                        "Phase 0  DONE   Foundation, infra, API surface",
                        "Phase 1  DONE   Document ingestion (parse + chunk)",
                        "Phase 2  TODO   Embeddings + pgvector storage",
                        "Phase 3  TODO   Retrieval + RAG answering",
                        "Phase 4  TODO   Guardrails + graceful fallback",
                        "Phase 5  TODO   Evaluation harness",
                        "Phase 6  TODO   Angular chat UI",
                        "Phase 7  TODO   Containerize + polish"
                )
        );
    }
}
