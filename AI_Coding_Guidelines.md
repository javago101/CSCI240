# Mandatory AI Coding Guidelines (CSCI240)

To maintain academic integrity and ensure that all generated code serves as a clear learning tool for a Data Structures course, the following rules are **STRICTLY ENFORCED** for all AI-generated content in this repository.

## 1. Academic Purity Over Industrial Optimization
*   **Fundamental Syntax Only**: Use standard `for`, `while`, and `if-else` blocks. 
*   **Prohibited Features**: Do **NOT** use Java Streams, Lambda expressions (`->`), method references (`::`), or any high-level functional programming abstractions unless specifically requested.
*   **Goodrich Alignment**: All data structure implementations must strictly follow the architectural patterns found in the *Goodrich, Tamassia, and Mount* textbook.

## 2. No "Hidden" Performance Tricks
*   **Explicit I/O**: Do not hide I/O costs. If the task is to time an operation, include the `Scanner` or `File` reading logic inside the timing block if that is the standard student approach.
*   **No Pre-Caching**: Do not pre-load data into memory arrays to "artificially" speed up hash map or tree tests, as this misleads the understanding of Big-O in a real-world system.

## 3. Transparency and Pedagogy
*   **Comment Every Step**: Use clear, natural language comments to explain the *purpose* of each logic block, especially if it relates to a specific PA requirement.
*   **Big-O Awareness**: Prioritize code clarity that reflects the theoretical Big-O complexity over micro-optimizations that obscure the algorithm.

## 4. Minimalist "Hand-Typed" Aesthetics (**DE-AI STYLE**)
*   **Avoid Over-Modularization**: For simple scripts, prefer a single, flattened `main` method. Do not split logic into 3-4 private helper methods if it makes the code look "too engineered."
*   **Direct console output**: Avoid highly structured ASCII headers or "industrial" banners (e.g., `=== Part A ===`). Use simple text prints or blank lines to separate output.
*   **Authentic Code Flow**: Code should feel like it was "hand-typed" during an iterative process. Use simple `throws Exception` for quick scripts instead of verbose `try-catch` blocks.
*   **Casual Comments**: Keep comments natural and sparse, focusing on "why" rather than "what," mimicking a student's personal notes rather than formal documentation.

---
*This document serves as a permanent architectural anchor. Any AI assistant entering this workspace must scan and obey these rules immediately.*
