# Continuation prompt — build SupportLens phase by phase

Paste everything below into a **new chat** to continue building the project.

---

I'm building **SupportLens**, an AI-powered support knowledge assistant, at
`c:\personal\SupportLens`. It's a **Retrieval-Augmented Generation (RAG)** service in
**Java 21 + Spring Boot 3.4.x + Spring AI 1.0.x**, using **PostgreSQL + pgvector** for
vector storage and **OpenAI** models (swappable to Azure OpenAI).

**Phase 0 is already scaffolded and should compile/run:** Spring Boot skeleton, Docker
Compose (Postgres+pgvector), `application.yml`, Flyway extensions migration, actuator
health, Swagger UI, controllers wired to stub services (`IngestionService`,
`RagService`) that currently throw `UnsupportedOperationException` (surfaced as HTTP 501),
and a `GlobalExceptionHandler`. Read `README.md` first — it has the architecture, the
full project structure, and a detailed **phased roadmap (Phase 0–7)**.

**Your task:** implement the project **one phase at a time**, following the README
roadmap, starting with **Phase 1**. After each phase, stop and summarize what changed so
I can review before you continue.

**Constraints / style:**

- Keep it **simple and easy to explain in an interview** — no over-engineering, no
  autonomous agents, no model training, no speculative abstractions.
- Stick to **Spring AI 1.0.x + Spring Boot 3.4.x** and **stable Spring AI APIs**
  (`TikaDocumentReader`, `TokenTextSplitter`, `EmbeddingModel`, `VectorStore.add`,
  `VectorStore.similaritySearch`, `SearchRequest`, `ChatClient`).
- The app must **always compile and run** after each phase; replace stubs incrementally.
- Update `MetaController`'s roadmap status and the README checkboxes as phases complete.
- Use only the **synthetic** docs in `sample-docs/` for testing — no real/confidential data.
- Don't invent metrics or make unmeasured claims; Phase 5 produces real numbers.
- Add/adjust tests as you go; keep the offline smoke tests green.

**Per phase, deliver:**

1. The code changes (with any new Flyway migration / entity / config).
2. How to run and verify it (exact commands + expected result).
3. A short "what to say about this in an interview" note.

Start with **Phase 1 — Document ingestion (parse + chunk)**. Before coding, briefly
confirm your plan and the files you'll add/modify, then implement it.
