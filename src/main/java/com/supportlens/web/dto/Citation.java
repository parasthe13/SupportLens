package com.supportlens.web.dto;

/**
 * A single source passage that supported the answer, so the user can verify it.
 *
 * @param source  the originating document (e.g. file name)
 * @param snippet the retrieved text passage
 * @param score   similarity score from the vector search (higher = more relevant)
 */
public record Citation(String source, String snippet, double score) {
}
