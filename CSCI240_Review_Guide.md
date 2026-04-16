# CSCI240 Cumulative Review & Deep-Dive Study Guide

Welcome to the **Comprehensive Review Document** for CSCI 240. This guide breaks down Programming Assignments 1 through 7, covering every major data structure, internal mechanics, Big-O time complexities, and mathematical algorithms you have learned this semester. 

This guide is heavily expanded to cover the **"under the hood"** details essential for acing midterms and finals!

---

## 📚 General Big-O Complexity Cheat Sheet

Before diving into the PAs, memorize these average-case time complexities which govern all the assignments:
| Data Structure | Access/Search | Insertion | Deletion | Notes |
|---|---|---|---|---|
| **Array** | $O(1)$ | $O(n)$ | $O(n)$ | Fast read, terrible insert without gaps. |
| **Singly Linked** | $O(n)$ | $O(1)$* | $O(1)$* | *Fast if inserting/deleting at Known nodes (Head/Tail). |
| **Stack/Queue** | N/A | $O(1)$ | $O(1)$ | Pure $O(1)$ amortized operations. |
| **Binary Tree** | $O(\log n)$ | $O(\log n)$ | $O(\log n)$ | Assuming balanced. If unbalanced, degrading to $O(n)$. |
| **Binary Heap** | $O(n)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ to find Min/Max. Very fast sorting. |
| **Hash Table** | $O(1)$ | $O(1)$ | $O(1)$ | Amortized. If Load Factor is ignored, degrades to $O(n)$. |

---

## PA1 & PA2: Java OOP, Arrays, & Node Linking
### Core Deep Dive
* **Generics `<E>`**: Used pervasively so that classes like `SinglyLinkedList<E>` can hold Integers, Strings, or Objects interchangeably without throwing `ClassCastException` later.
* **Nodes**: The building block of dynamic memory. A Singly Linked Node possesses two things: `element` (the data) and `next` (a pointer to the next node).
  * **Memory Hazard**: If you lose the `head` pointer, the entire list is lost and garbage collected.
  * **Doubly Linked Lists**: Adds a `prev` pointer. It makes deletion at the tail $O(1)$ since you don't have to traverse from head to find the second-to-last node!

## PA3: Positional Lists & Iterators
### Core Deep Dive
* **The Position Abstraction**: Why do we use `Position<E>`? If a user wants to delete an element in the middle of a standard List, they have to loop $O(n)$ to find it. But if the list returns a `Position` token back to the user, the user can say `list.remove(positionMarker)`, and the underlying algorithm does it in **$O(1)$** time because the `Position` secretly holds the raw memory address.
* **Iterators**: `hasNext()` and `next()`. Understand that `Iterable` means an object *can return* an `Iterator`, while `Iterator` is the object actively traversing.

## PA4: Abstract Storage (Stacks, Queues, Deques)
### Core Deep Dive
* **LIFO (Stacks)**: Think of method call stacks or undo mechanisms. Usually strictly $O(1)$ via Arrays (tracking a single `t` top index) or Linked Lists (inserting at head).
* **FIFO (Queues)**: Think of OS tasks or ticketing. 
  * **Circular Array Formula**: Essential exam question! If implementing a Queue with an Array, when the `tail` index hits `N-1`, it must wrap around. The mathematical formula is: `nextIndex = (currentIndex + 1) % N`. 
* **Deques**: You can insert or remove from BOTH ends in $O(1)$ time natively.

## PA5: Generic Trees & Binary Trees
### Core Deep Dive
* **Binary Node Architecture**: Made of `element`, `parent`, `left`, `right`.
* **Tree Depth and Height Formulas**:
  * **Depth** of $p$: How many ancestors are above it. `if (p == root) return 0; else return 1 + depth(parent(p))`.
  * **Height** of tree: The maximum depth of any leaf. 
* **Traversals (Crucial for Exams)**:
  1. **Pre-order (N-L-R)**: Root is visited *before* children. Used for duplicating a tree.
  2. **In-order (L-N-R)**: Left child, then Root, then Right. In a Binary Search Tree, this prints elements in strictly increasing sorted order!
  3. **Post-order (L-R-N)**: Root is visited *after* children. Used for deleting a tree or calculating folder sizes bottom-up.

## PA6: Priority Queues & Heaps
### Core Deep Dive
* **Priority Queue Bottleneck**: In PA6, we proved why standard arrays fail. Sorting 100k items using an Array takes $O(n^2)$ (about 30 seconds), but a Binary Heap takes $O(n \log n)$ (about 100ms).
* **The Array-Based Heap Map**: Rather than using linked nodes, Heaps map a binary tree mathematically onto a flat array:
  * Left child of index `i` is at: `2i + 1`
  * Right child of index `i` is at: `2i + 2`
  * Parent of index `i` is at: `(i - 1) / 2`
* **Upheap & Downheap**: 
  * Inserting: place element at array end, then *Upheap* (bubble up) taking $O(\log n)$.
  * Removing Min: swap root with array end, delete end, then *Downheap* (bubble down) taking $O(\log n)$.

## PA7: Hash Maps & Collision Mathematics
### Core Deep Dive
* **Load Factor (`LF`)**: The ratio of `Size / Capacity`. If LF exceeds $0.75$, collisions drastically increase and the HashMap triggers typically an expensive `resize()` scaling array size by $2\times$.
* **Hash Functions**: A hash function has two parts: `HashCode` (creates integer) + `Compression Map` (fits integer into array bounds).
  * **Cyclic-Shift Hash Codes**: Bitwise operators `(hash << shift) | (hash >>> 32 - shift)`. We proved that a shift of `0` is catastrophically bad because "cat" and "act" would result in the exact same index. 
  * **MAD (Multiply-Add-Divide)** Compression: `[(a * hash + b) % prime] % capacity`. Using random primes scatters numbers excellently.
* **Collision Resolution**:
  * **Separate Chaining (What we built)**: Each bucket points to a mini ArrayList or Map. If a bucket gets 5 items, searching it takes 5 probes. Safe, but consumes more memory.
  * **Open Addressing / Probing**: Finding the next empty slot physically down the array. (Linear Probing $i+1$, Quadratic Probing $i^2$). If the table gets full, insertion causes infinite loops.
