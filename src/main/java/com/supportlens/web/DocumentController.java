package com.supportlens.web;

import com.supportlens.ingestion.IngestionService;
import com.supportlens.web.dto.DocumentSummary;
import com.supportlens.web.dto.IngestResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Knowledge-base management: upload documents and list what has been ingested.
 * (Behaviour is filled in during Phase 1 & 2.)
 */
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final IngestionService ingestionService;

    public DocumentController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    /** Upload a PDF/Markdown/text document to add it to the knowledge base. */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngestResponse upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return ingestionService.ingest(file);
    }

    /** List documents currently in the knowledge base. */
    @GetMapping
    public List<DocumentSummary> list() {
        return ingestionService.listDocuments();
    }
}
