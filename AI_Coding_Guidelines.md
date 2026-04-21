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

## 4. Minimalist "Hand-Typed" Aesthetics
*   **Flattened Logic**: Prioritize concise, direct logic over deep abstraction. If a loop in `main` suffices, avoid creating unnecessary helper methods.
*   **Authentic Style**: Code should feel like it was "hand-typed" during an iterative learning process. Use simple `throws Exception` for quick scripts instead of complex `try-catch` blocks.
*   **Sparse Comments**: Keep comments natural and focused on "why" rather than "what," mimicking a student's notes rather than a professional API documentation.

---
*This document serves as a permanent architectural anchor. Any AI assistant entering this workspace must scan and obey these rules immediately.*
