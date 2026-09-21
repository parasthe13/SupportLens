package com.supportlens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * SupportLens — an AI-powered support knowledge assistant.
 *
 * <p>It answers support questions from your own technical documentation using
 * Retrieval-Augmented Generation (RAG): documents are chunked and embedded into
 * a pgvector store, and each question retrieves the most relevant passages which
 * are handed to an LLM to produce a source-cited answer.</p>
 *
 * <p>See README.md for the phased roadmap. Phase 0 (this skeleton) boots the app,
 * wires infrastructure, and exposes the API surface; later phases fill in the
 * ingestion, retrieval, and answering logic.</p>
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class SupportLensApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupportLensApplication.class, args);
    }
}
