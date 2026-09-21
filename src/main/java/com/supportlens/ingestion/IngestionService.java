package com.supportlens.ingestion;

import com.supportlens.web.dto.DocumentSummary;
import com.supportlens.web.dto.IngestResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Turns an uploaded document into searchable knowledge.
 *
 * <p>Implemented across Phase 1 (parse + chunk + persist metadata) and
 * Phase 2 (embed each chunk and store the vectors in pgvector).</p>
 */
public interface IngestionService {

    /** Parse, chunk, embed, and store a document. */
    IngestResponse ingest(MultipartFile file);

    /** List documents that have been ingested. */
    List<DocumentSummary> listDocuments();
}
