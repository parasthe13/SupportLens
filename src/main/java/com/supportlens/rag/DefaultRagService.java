package com.supportlens.rag;

import com.supportlens.web.dto.ChatResponse;
import org.springframework.stereotype.Service;

/**
 * Phase 0 placeholder wiring for {@link RagService}.
 *
 * <p>TODO Phase 3: retrieve passages with {@code VectorStore.similaritySearch(...)},
 * build a prompt that injects them as context, call the LLM through Spring AI's
 * {@code ChatClient}, and map the retrieved passages into {@code Citation}s.</p>
 *
 * <p>TODO Phase 4: drop passages below the configured similarity threshold and,
 * when nothing relevant remains, return a grounded=false "not enough information"
 * response instead of letting the model guess.</p>
 */
@Service
public class DefaultRagService implements RagService {

    @Override
    public ChatResponse answer(String question, int topK) {
        throw new UnsupportedOperationException(
                "RAG answering arrives in Phase 3 & 4 — see the roadmap in README.md");
    }
}
