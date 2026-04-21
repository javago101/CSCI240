# CSCI240 - English Concepts & Glossary

Use this document to quickly memorize the standard English computer-science definitions required for answering theoretical exam questions ranging from Lists and Trees to Hash Maps.

## 1. Abstract Data Types (ADT) vs Implementations
- **ADT (Abstract Data Type)**: A theoretical model that specifies what operations can be performed (e.g., `Stack` can `push`, `pop`, `top`), but *not* how they are implemented.
- **Data Structure**: The concrete code implementation of an ADT (e.g., executing a `Stack` using a `SinglyLinkedList` or a standard `Array`).

## 2. Memory Organizations
- **Arrays**: Fixed contiguous blocks of memory. Great for fast $O(1)$ indexed reads, but resizing is an expensive $O(N)$ operation.
- **Linked Lists**: Disjoint pieces of memory tied together through reference pointers (`next`, `prev`). Resizing is free and dynamic, but reading $i$-th element is slow $O(N)$ because you must traverse from the head.

## 3. Trees & Hierarchies
- **Binary Tree**: A tree where every node has at most two children (Left and Right).
- **Depth**: The number of ancestors a node has (Root has depth 0).
- **Height**: The maximum depth of any leaf in the entire tree.
- **Binary Search Tree (BST)**: A binary tree where the Left child is always strictly *less* than its Parent, and the Right child is strictly *greater* than its Parent.
- **Traversals**:
  - **In-order Traversal**: Left -> Root -> Right. (Produces sorted output for a BST).
  - **Pre-order Traversal**: Root -> Left -> Right. 
  - **Post-order Traversal**: Left -> Right -> Root.

## 4. Priority Queues & Heaps
- **Priority Queue**: An ADT mapping where elements are assigned priorities, and removing elements strictly extracts the one with the highest priority first, regardless of insertion order.
- **Binary Heap**: A specialized Binary Tree designed to compactly array-implement Priority Queues. 
  - **Min-Heap Property**: A parent is always $\le$ both of its children.
  - **Complete Tree**: Every level is completely filled except the bottom row, which is filled left-to-right.
  - **Upheap (Bubble Up)**: Moving a newly inserted leaf up the hierarchy until the Min-Heap property is restored.
  - **Downheap (Bubble Down)**: Moving a new root down the hierarchy to replace extracted roots.

## 5. Hash Tables & Architectures
- **Hash Map (Table)**: An associative array resolving key-value lookups in $O(1)$ amortized time.
- **Hash Function**: The mechanism linking keys to array buckets. Composed of two distinct steps:
  1. **Hash Code**: Turns abstract keys (Strings/Objects) into a random integer. (e.g., Horner's Polynomial Accumulation, Cyclic Shifts).
  2. **Compression Function**: Shrinks the integer to safely fit inside the array limit (`hash % capacity`). **MAD (Multiply-Add-Divide)** uses random primes to compress while minimizing overlaps.
- **Collision**: Math failure where two distinct keys evaluate to the exact same bucket index.
- **Load Factor ($\lambda$)**: Equation $\lambda = \frac{\text{Entries (N)}}{\text{Capacity (C)}}$. In Java, standard max limit is $0.75$.
- **Rehashing**: The act of pausing the application, allocating double the memory array size, and forcibly recalculating and redistributing every single piece of data when the $\lambda$ limit is breached.

### Collision Resolution Strategies
- **Separate Chaining**: (The `ChainHashMap`). If variables collide at Index 5, you build a miniature memory bucket (Linked list or array) out of Index 5. Safe linearly, wastes RAM space.
- **Open Addressing (Probing)**: (The `ProbeHashMap`). If variables collide at Index 5, the program physically slides down the main array to find the next available empty spot.
  - **Linear Probing**: Searches $i+1, i+2, i+3$... Prone to primary "clustering".
  - **Quadratic Probing**: Searches $i+1^2, i+2^2, i+3^2$...
