package com.supportlens.ingestion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * A document that has been ingested into the knowledge base. One document owns
 * many {@link ChunkEntity} rows.
 */
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String filename;

    private String contentType;

    @Column(nullable = false)
    private int chunkCount;

    @Column(nullable = false)
    private Instant ingestedAt;

    protected DocumentEntity() {
        // required by JPA
    }

    public DocumentEntity(String filename, String contentType, int chunkCount, Instant ingestedAt) {
        this.filename = filename;
        this.contentType = contentType;
        this.chunkCount = chunkCount;
        this.ingestedAt = ingestedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    public int getChunkCount() {
        return chunkCount;
    }

    public Instant getIngestedAt() {
        return ingestedAt;
    }
}
