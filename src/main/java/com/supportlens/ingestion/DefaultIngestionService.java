package com.supportlens.ingestion;

import com.supportlens.web.dto.DocumentSummary;
import com.supportlens.web.dto.IngestResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Phase 1 ingestion: parse an uploaded file into text (Apache Tika), split it
 * into token-bounded chunks (Spring AI {@link TokenTextSplitter}), and persist
 * the document plus its ordered chunks.
 *
 * <p>TODO Phase 2: also embed each chunk and write the vectors to pgvector via
 * {@code VectorStore.add(...)} so the chunks become semantically searchable.</p>
 */
@Service
public class DefaultIngestionService implements IngestionService {

    private final DocumentRepository documentRepository;
    private final ChunkRepository chunkRepository;

    public DefaultIngestionService(DocumentRepository documentRepository, ChunkRepository chunkRepository) {
        this.documentRepository = documentRepository;
        this.chunkRepository = chunkRepository;
    }

    @Override
    @Transactional
    public IngestResponse ingest(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("A non-empty 'file' is required.");
        }

        List<String> chunkTexts = parseAndChunk(file);
        if (chunkTexts.isEmpty()) {
            throw new IllegalArgumentException("No extractable text found in the uploaded file.");
        }

        DocumentEntity document = documentRepository.save(new DocumentEntity(
                file.getOriginalFilename(),
                file.getContentType(),
                chunkTexts.size(),
                Instant.now()));

        List<ChunkEntity> chunks = new ArrayList<>(chunkTexts.size());
        for (int index = 0; index < chunkTexts.size(); index++) {
            chunks.add(new ChunkEntity(document.getId(), index, chunkTexts.get(index)));
        }
        chunkRepository.saveAll(chunks);

        return new IngestResponse(
                document.getId().toString(),
                document.getFilename(),
                chunks.size(),
                "INGESTED");
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentSummary> listDocuments() {
        return documentRepository.findAllByOrderByIngestedAtDesc().stream()
                .map(entity -> new DocumentSummary(
                        entity.getId().toString(),
                        entity.getFilename(),
                        entity.getChunkCount(),
                        entity.getIngestedAt()))
                .toList();
    }

    private List<String> parseAndChunk(MultipartFile file) {
        Resource resource = new ByteArrayResource(readBytes(file)) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        List<Document> parsed = new TikaDocumentReader(resource).get();
        List<Document> chunks = TokenTextSplitter.builder().build().apply(parsed);

        return chunks.stream()
                .map(Document::getText)
                .filter(text -> text != null && !text.isBlank())
                .toList();
    }

    private byte[] readBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to read uploaded file", exception);
        }
    }
}
