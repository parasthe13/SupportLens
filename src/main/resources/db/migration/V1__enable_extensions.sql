-- Phase 0: enable the extensions pgvector and the vector store rely on.
-- These are idempotent and safe to run repeatedly. The pgvector/pgvector
-- Docker image ships with the `vector` extension available.
-- Phase 1 will add V2__documents.sql for document/chunk metadata.

CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
