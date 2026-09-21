package com.supportlens.web.dto;

import java.util.List;

/**
 * Answer returned to the caller.
 *
 * @param answer    the generated answer text
 * @param grounded  true if the answer was supported by retrieved context; false when the
 *                  system fell back to "not enough information" (Phase 4 guardrail)
 * @param citations the source passages used to build the answer
 * @param latencyMs end-to-end time to produce the answer, for performance visibility
 */
public record ChatResponse(
        String answer,
        boolean grounded,
        List<Citation> citations,
        long latencyMs
) {
}
