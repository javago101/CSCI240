# 📋 CSCI240 Master PA Question Tracker (PA1 - PA8)

> **Auto-Tracking Rule**: This document is the source of truth for all theoretical questions from Programming Assignments. Every time a PA is completed, its questions MUST be appended here for centralized exam review.

---

## 🟢 PA1: Object-Oriented Design
**Question: What are the primary benefits of using a dedicated Class instead of loose variables?**  
**Answer**: Encapsulation and Data Integrity. By making fields private, we prevent illegal external modifications.

**Insight**: Preventing users from directly binary-modifying a balance without going through `charge()` or `makePayment()` is the core of software security.

---

## 🟡 PA2: Linear Data Structures
**Question: Compare the efficiency of the two Prefix Average algorithms.**  
**Answer**: Algorithm 1 is $O(n^2)$ due to nested loops. Algorithm 2 is $O(n)$ by maintaining a running sum.

**Insight**: In a Singly Linked List, losing the `Head` pointer results in a memory leak.

---

## 🟠 PA3: Positional Abstractions
**Question: Why is the `Position` interface superior to integer indices?**  
**Answer**: Integer indices require $O(n)$ shifts for insertions. A `Position` refers to a memory address, allowing $O(1)$ updates.

---

## 🔵 PA4: Stacks & Queues
**Question: Why is the Modulo Operator (%) required for an array-based Queue?**  
**Answer**: To enable a "Circular Array," allowing the tail to wrap back to index 0 for memory reuse.

---

## 🟣 PA5: Hierarchical Structures
**Question: Which traversal is best for a File System?**  
**Answer**: **Pre-order Traversal**. It visits the folder before its contents.

**Trace**: BFS uses a Queue to explore level-by-level (Level-order).

---

## 🟤 PA6: Priority Queues & Heaps
**Question: Why does Heap Sort outperform Selection Sort for 100k items?**  
**Answer**: Selection Sort is $O(n^2)$, while Heap Sort is $O(n \log n)$.

---

## ⚫ PA7: Hashing & Performance
**Question: Why do we need to use compression functions?**  
**Answer**: To map a large 32-bit hash code into the limited range of array indices $[0, N-1]$.

**Common Functions**: Division Method (`% N`) and MAD (`[(ay+b)%p]%N`).

---

## 🔴 PA8: Trees & Balancing (AVL/BST)
**Question 1: BST Trace (Insert 10, 20, 4, 8, 15 -> Erase 8 -> Erase 10)**  
**Final Result**: Root: 15, Left: 4, Right: 20. (Successor 15 replaces 10).

**Question 2: AVL Trace (Insert 10, 20, 30, 15, 12 -> Erase 30)**  
**Final Result**: Root: 12, Left: 10, Right: 20, 20-Left: 15. (Single Right Rotation at root 20).

---
