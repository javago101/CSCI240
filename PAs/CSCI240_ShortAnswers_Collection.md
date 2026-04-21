# CSCI240 - Consolidated Short Answer Questions & Solutions (PA1-PA7)

This document gathers all theoretical questions, algorithm analyses, and experiment reflections from Programming Assignments 1 through 7.

---

## 🟢 PA1: Object-Oriented Design
### **Q&A**
**Question: What are the primary benefits of using a dedicated Class instead of loose variables?**  
**Answer**: Encapsulation and Data Integrity. By making fields private, we prevent illegal external modifications.

### **Lab Observations: The Credit Card Hook**
- **Insight**: Preventing users from directly binary-modifying a balance without going through `charge()` or `makePayment()` is the core of software security and robust design.

---

## 🟡 PA2: Linear Data Structures
### **Q&A**
**Question: Compare the efficiency of the two Prefix Average algorithms.**  
**Answer**: Algorithm 1 is $O(n^2)$ due to nested loops. Algorithm 2 is $O(n)$ by maintaining a running sum, proving that optimized logic can transform performance from quadratic to linear.

### **Lab Observations: Memory Safety**
- **Insight**: In a Singly Linked List, losing the `Head` pointer results in a memory leak, as the JVM loses the only bridge to the data.

---

## 🟠 PA3: Positional Abstractions
### **Q&A**
**Question: Why is the `Position` interface superior to integer indices?**  
**Answer**: Integer indices require $O(n)$ shifts for array insertions. A `Position` refers to a memory address, allowing instant $O(1)$ updates.

---

## 🔵 PA4: Stacks & Queues
### **Q&A**
**Question: Why is the Modulo Operator (%) required for an array-based Queue?**  
**Answer**: To enable a "Circular Array." It allows the tail to wrap back to index 0, maximizing memory reuse.

---

## 🟣 PA5: Hierarchical Structures
### **Q&A**
**Question: Which traversal is best for a File System?**  
**Answer**: **Pre-order Traversal** (Root -> Left -> Right). It visits the folder before its contents, matching visual directory hierarchies.

### **Lab Observations: BFS Trace**
- **Trace**: BFS uses a Queue to explore layer-by-layer (Level-order), ensuring we visit all siblings before moving to the next depth level.

---

## 🟤 PA6: Priority Queues & Heaps
### **Q&A**
**Question: Why does Heap Sort outperform Selection Sort for 100k items?**  
**Answer**: Selection Sort is $O(n^2)$ (slow), while Heap Sort is $O(n \log n)$ (fast). For 100k items, this is the difference between minutes and milliseconds.

---

## ⚫ PA7: Hashing & Performance
### **Q&A**
**Question 1: Do the collected times for Exercises 1 and 2 make sense? Explain why or why not.**  
**Answer**: **Yes**, the times make sense. Both implementations show $O(N)$ total growth for $N$ total elements, which verifies the **$O(1)$ amortized insertion** property of hash tables. The custom `ChainHashMap` is slightly slower than the native `java.util.HashMap` because the native version is highly optimized (using JIT, memory packing, and internal tree-conversions for large buckets), but they scale identically.

**Question 2: Why do we need to use compression functions? What are some common compression functions?**  
**Answer**: 
- **Purpose**: A hash code can be any 32-bit integer. A compression function is needed to map this large range safely into the limited range of valid array indices $[0, N-1]$. 
- **Common Functions**: 
    1. **Division Method**: `hash % N`. 
    2. **MAD (Multiply-Add-Divide)**: `[(ay + b) % p] % N`, which uses random primes to more effectively scramble bit patterns and reduce clustering.

### **Lab Observations: Mathematical Collisions**
- **Insight 1 (Polynomials)**: When the parameter $a=1$, the hash becomes a simple sum of ASCII values. This causes words like "dog" and "god" to collide, leading to **178 collisions** in a small dataset.
- **Insight 2 (Cyclic Shifts)**: A shift of `0` failed to mix bits across positions, resulting in the same catastrophic collision rate.
- **Insight 3 (Load Factor)**: Using a capacity of `size / 0.75` effectively balances memory usage with collision probability, preventing performance degradation.
