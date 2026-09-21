package com.supportlens.web.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Incoming chat question. {@code topK} is optional; when null the configured
 * default from {@link com.supportlens.config.RagProperties} is used.
 */
public record ChatRequest(
        @NotBlank(message = "question must not be blank") String question,
        Integer topK
) {
}
