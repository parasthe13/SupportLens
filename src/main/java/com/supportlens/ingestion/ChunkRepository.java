package com.supportlens.ingestion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChunkRepository extends JpaRepository<ChunkEntity, UUID> {
}
