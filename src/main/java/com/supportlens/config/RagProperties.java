package com.supportlens.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Tunable RAG parameters, bound from the {@code supportlens.rag.*} keys in
 * application.yml. Keeping these in config (not hard-coded) lets you tune
 * retrieval quality without recompiling.
 *
 * @param topK                number of passages to retrieve per question
 * @param similarityThreshold minimum cosine similarity for a passage to count as relevant (Phase 4 guardrail)
 * @param systemPrompt        instruction given to the LLM about how to answer and cite
 */
@ConfigurationProperties(prefix = "supportlens.rag")
public record RagProperties(
        @DefaultValue("4") int topK,
        @DefaultValue("0.5") double similarityThreshold,
        @DefaultValue("") String systemPrompt
) {
}
