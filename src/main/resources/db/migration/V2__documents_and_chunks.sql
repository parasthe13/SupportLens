-- Phase 1: document + chunk metadata.
-- The vector embeddings live in the Spring-AI-managed `vector_store` table
-- (auto-created); these tables track the human-facing document record and the
-- ordered text chunks it was split into.

CREATE TABLE documents (
    id           UUID        PRIMARY KEY,
    filename     TEXT        NOT NULL,
    content_type TEXT,
    chunk_count  INTEGER     NOT NULL,
    ingested_at  TIMESTAMPTZ NOT NULL
);

CREATE TABLE chunks (
    id          UUID    PRIMARY KEY,
    document_id UUID    NOT NULL REFERENCES documents(id) ON DELETE CASCADE,
    chunk_index INTEGER NOT NULL,
    content     TEXT    NOT NULL
);

CREATE INDEX idx_chunks_document_id ON chunks(document_id);
