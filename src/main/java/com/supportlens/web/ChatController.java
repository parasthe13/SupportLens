package com.supportlens.web;

import com.supportlens.config.RagProperties;
import com.supportlens.rag.RagService;
import com.supportlens.web.dto.ChatRequest;
import com.supportlens.web.dto.ChatResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ask a question and get a source-cited answer from the knowledge base.
 * (Behaviour is filled in during Phase 3 & 4.)
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final RagService ragService;
    private final RagProperties ragProperties;

    public ChatController(RagService ragService, RagProperties ragProperties) {
        this.ragService = ragService;
        this.ragProperties = ragProperties;
    }

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        int topK = request.topK() != null ? request.topK() : ragProperties.topK();
        return ragService.answer(request.question(), topK);
    }
}
