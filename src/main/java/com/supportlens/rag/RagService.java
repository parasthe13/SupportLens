package com.supportlens.rag;

import com.supportlens.web.dto.ChatResponse;

/**
 * Answers a question with Retrieval-Augmented Generation: retrieve the most
 * relevant document passages, then ask the LLM to answer using only those
 * passages and cite them.
 *
 * <p>Implemented across Phase 3 (retrieve + prompt + generate) and Phase 4
 * (similarity-threshold guardrail + graceful "not enough information" fallback).</p>
 */
public interface RagService {

    /**
     * @param question the user's question
     * @param topK     how many passages to retrieve as context
     * @return the answer plus the citations that support it
     */
    ChatResponse answer(String question, int topK);
}
