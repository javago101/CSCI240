# CSCI240 Cumulative Review & Study Guide

Welcome to your ultimate review document for CSCI 240! You have successfully conquered **Programming Assignments 1 through 7**. The combination of these PAs fully captures the architectural ascent from basic variables all the way up to complex algorithm collision handling.

Use this guide to align your final learning goals, track the conceptual workload, and review the pivotal learning points ahead of exams.

---

## 📚 General Course Workload Profile
**Total Practical PAs Completed**: 7 (Plus numerous Full-Credit Extra Credit Modules)
**Code Strategy Used**: Native IDE Compilation (`javac`) & Textbook Package integration (`net.datastructures.*`)
**Overall Completion**: 100%

---

## PA1: Java Primer & Object-Oriented Principles
* **Core Topics**: Encapsulation, Classes, Polymorphism.
* **Key Files**: `CreditCard.java`, `Progression.java`
* **Learning Points**:
  - Setting up constructor syntax and overriding methods (`toString()`, `clone()`).
  - Leveraging Java's core inheritance modeling to prevent redundant code.

## PA2: Fundamental Arrays & Node Structuring
* **Core Topics**: Array traversals, Primitive Singly Linked Lists.
* **Key Files**: `PA2_EX*` suite.
* **Learning Points**:
  - Memory difference between rigid Array indexes versus dynamic `Node` configurations.
  - Linking un-initialized space pointers (`node.next`).

## PA3: Positional Lists & Iterators
* **Core Topics**: Decoupling the "node index" using Position handles.
* **Key Files**: LinkedPositionalList tests (`PA3_Ex2, PA3_Ex3`).
* **Learning Points**:
  - How an abstraction like `Position<E>` allows users to safely track list items without knowing array offsets or exposing internal memory Nodes.
  - Using iterators to rapidly cycle lists.

## PA4: Abstract Storage (Stacks, Queues, Deques)
* **Core Topics**: LIFO / FIFO architectural implementation.
* **Key Files**: `MyArrayStack.java`, `MyLinkedQueue.java`, `MyLinkedDeque.java`
* **Learning Points**:
  - Realizing Stacks (Last In, First Out) are perfect for back-tracking apps and Queues (First In, First Out) handle OS job logic.
  - Deques (Double-ended Queues) prove how versatile Linked Nodes become when you map `prev` and `next` limits correctly.

## PA5: Advanced Hierarchies (Binary Trees)
* **Core Topics**: Tree logic and Recursive Traversal.
* **Key Files**: `LinkedBinaryTree.java`, `PA5_Ex3.java`
* **Learning Points**:
  - Navigating left vs right children.
  - Mastering the three primary depth traversals: Pre-order (Node -> L -> R), In-order (L -> Node -> R), Post-order (L -> R -> Node). Essential for exam analysis.

## PA6: Priority Queues, Heaps & Scaling Factors $O(N \log N)$
* **Core Topics**: Time complexity, Min/Max comparators, standard PQs vs Heaps.
* **Key Files**: `SortedPriorityQueue` vs `HeapPriorityQueue`.
* **Learning Points**:
  - Implementing custom Comparators to flip sorts (descending strings etc).
  - Experiencing firsthand how a standard Queue faces a critical bottleneck tracing arrays ($O(n^2)$), compared to the extreme efficiency of a proper Binary Heap sorting 10万 entries near-instantly ($O(N \log N)$).

## PA7: Hash Maps, Collision Mathematics & Probing
* **Core Topics**: Dispersing keys optimally across arrays.
* **Key Files**: Native `HashMap`, Textbook `ChainHashMap`, `polynomial/cyclicHash`.
* **Learning Points**:
  - If a map gets too loaded, lookup speeds degrade linearly toward $O(N)$ due to linked list collisions ("Probing").
  - The parameter of the hash matters universally: a simple cyclic shift of 0 completely destroys data dispersion, proving random distribution theories mathematically.

---

### 🚀 Final Review Strategies for Exams:
1. **Focus on Trees vs PQs**: Be able to trace a Priority Queue `removeMin()` operation mentally. Know how it flips the last node to root, then "Bubbles Down".
2. **Hash Definitions**: Memorize what "Load Factor" (LF) refers to computationally (`LF = Size / Array Capacity`). Know why 0.75 is the industry standard default. 
3. **Execution Speeds**: Be able to write out the Big-O notations for worst-case inserts on Arrays vs Hashes vs Binary Trees.
