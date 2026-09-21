# Sample documents

Drop the documents you want SupportLens to answer from into this folder
(PDF, Markdown, or plain text).

Use **synthetic or public** documentation only — never confidential or
employer-owned material. The included `order-troubleshooting.md` is a small,
made-up support guide you can use to test the ingestion and chat pipeline once
Phase 1–3 are implemented.

Once ingestion (Phase 1) is running, upload a file with:

```bash
curl -F "file=@sample-docs/order-troubleshooting.md" http://localhost:8080/api/documents
```
