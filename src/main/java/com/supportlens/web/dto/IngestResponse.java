package com.supportlens.web.dto;

/**
 * Result of ingesting one uploaded document.
 *
 * @param documentId server-assigned id for the stored document
 * @param filename   original file name
 * @param chunkCount number of chunks the document was split into
 * @param status     ingestion outcome (e.g. "INGESTED")
 */
public record IngestResponse(
        String documentId,
        String filename,
        int chunkCount,
        String status
) {
}
