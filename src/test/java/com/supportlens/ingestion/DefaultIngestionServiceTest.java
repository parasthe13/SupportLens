package com.supportlens.ingestion;

import com.supportlens.web.dto.IngestResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Phase 1 unit test. Runs offline: it parses/chunks a small in-memory document
 * with the real Tika + TokenTextSplitter, and mocks the repositories so no
 * database is required.
 */
class DefaultIngestionServiceTest {

    private final DocumentRepository documentRepository = mock(DocumentRepository.class);
    private final ChunkRepository chunkRepository = mock(ChunkRepository.class);
    private final DefaultIngestionService service =
            new DefaultIngestionService(documentRepository, chunkRepository);

    @Test
    void ingestParsesChunksAndPersists() {
        when(documentRepository.save(any(DocumentEntity.class))).thenAnswer(invocation -> {
            DocumentEntity document = invocation.getArgument(0);
            ReflectionTestUtils.setField(document, "id", UUID.randomUUID()); // simulate JPA id generation
            return document;
        });

        String text = "SupportLens ingestion test. ".repeat(20);
        MockMultipartFile file = new MockMultipartFile(
                "file", "notes.txt", "text/plain", text.getBytes(StandardCharsets.UTF_8));

        IngestResponse response = service.ingest(file);

        assertEquals("notes.txt", response.filename());
        assertEquals("INGESTED", response.status());
        assertTrue(response.chunkCount() >= 1);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<ChunkEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(chunkRepository).saveAll(captor.capture());
        assertEquals(response.chunkCount(), captor.getValue().size());
    }

    @Test
    void ingestRejectsEmptyFile() {
        MockMultipartFile empty = new MockMultipartFile(
                "file", "empty.txt", "text/plain", new byte[0]);

        assertThrows(IllegalArgumentException.class, () -> service.ingest(empty));
    }
}
