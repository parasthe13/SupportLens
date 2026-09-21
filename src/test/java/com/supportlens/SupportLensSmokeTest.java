package com.supportlens;

import com.supportlens.rag.DefaultRagService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Offline smoke tests (no database, no API key). These document the current
 * contract for features that are still stubbed. As each phase lands, its stub
 * assertion here is replaced by a real test — see
 * {@code com.supportlens.ingestion.DefaultIngestionServiceTest} for the Phase 1
 * example. A full {@code @SpringBootTest} with Testcontainers can be added in Phase 7.
 */
class SupportLensSmokeTest {

    @Test
    void ragAnsweringIsNotImplementedYet() {
        DefaultRagService service = new DefaultRagService();
        assertThrows(UnsupportedOperationException.class, () -> service.answer("hello?", 4));
    }
}
