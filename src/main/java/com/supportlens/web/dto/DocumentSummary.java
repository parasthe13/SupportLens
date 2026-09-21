package com.supportlens.web.dto;

import java.time.Instant;

/**
 * Lightweight view of an ingested document, used by the "list documents" endpoint.
 */
public record DocumentSummary(
        String id,
        String filename,
        int chunkCount,
        Instant ingestedAt
) {
}
