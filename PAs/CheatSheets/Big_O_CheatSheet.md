# CSCI240 - Final Exam Big-O Constants Matrix

Mastering standard Big-O average and worst-case complexities is 100% required for standard multiple-choice sections on data structures testing.

Below is the absolute matrix of Time Complexities mapped directly across your curriculum.

> **Legend**: 
> - $N$ = Number of elements in standard collections.
> - $K$ = Number of identical keys (if duplicates exist).
> - $h$ = Height of a tree structure.
> - $\star$ = Denotes amortized time (Occasional burst costs distributed over free actions).

## 1. Linear Implementations

| Data Structure / Component | Access / Search | Insertion (Head/Tail) | Deletion (End) | Deletion (Middle) | Notes |
|---|---|---|---|---|---|
| **Regular Array** | $O(1)$ | $O(N)$ (if shifting) | $O(1)$ | $O(N)$ | Native memory blocking, instant access by index. | 
| **Singly Linked Node List**| $O(N)$ | $O(1)$ | $O(N)$ | $O(N)$ | Cannot find tail deletion instantly without traversing. |
| **Doubly Linked Node List**| $O(N)$ | $O(1)$ | $O(1)$ | $O(1)^*$ | *Only $O(1)$ if you already possess a pointer to the specific node. |
| **Stack (Array or Linked)** | N/A | $O(1)$ | $O(1)$ | N/A | LIFO architecture limits all actions physically to the top/head. |
| **Queue (Circular Array)** | N/A | $O(1)$ | $O(1)$ | N/A | FIFO limits; uses modulo mathematics for limits. |

## 2. Advanced Priority Architectures

| Approach Used | Insert Action | Extract-Min / Max | Finding Min | Construction phase |
|---|---|---|---|---|
| **Unsorted List** | $O(1)$ | $O(N)$ | $O(N)$ | $O(N)$ |
| **Sorted Array/List** | $O(N)$ | $O(1)$ | $O(1)$ | $O(N^2)$ or $O(N \log N)$ |
| **Binary Heap Array** | $O(\log N)$ | $O(\log N)$ | $O(1)$ | $O(N \log N)$ |

*Note: Sorting an entire dataset ($N$ items) entirely using a Priority Queue fundamentally behaves as $O(N^2)$ using Unsorted setups, but shoots up to extremely fast $O(N \log N)$ when applied on top of Binary Heaps (HeapSort).*

## 3. Trees and Search Hierarchies

| Tree Structure | Space Storage | Access (Search) | Insertion | Deletion |
|---|---|---|---|---|
| **Binary Tree Traversal** | $O(N)$ | $O(N)$ | --- | --- |
| **Vanilla Binary Search Tree** | $O(N)$ | $O(h)$ | $O(h)$ | $O(h)$ |
| **Balanced Tree (AVL/RBT)** | $O(N)$ | $O(\log N)$ | $O(\log N)$ | $O(\log N)$ |

*Note: For Vanilla Binary Search Trees, worst case height $h = N$ (if items are inserted consecutively like `1, 2, 3, 4` forming a straight line). Best/Balanced case is $h = \log N$.*

## 4. Hash Tables (Unordered Maps)

| Collision Matrix Rule | Get/Access | Put/Insert | Remove/Delete | Note Status |
|---|---|---|---|---|
| **Expected Average Case** | $O(1)^\star$ | $O(1)^\star$ | $O(1)^\star$ | Happens if Load Factor < $0.75$ and Random hashing is good. |
| **Worst Cased Scenario** | $O(N)$ | $O(N)$ | $O(N)$ | Happens if Load Factor is ignored OR Hash shift = 0 (putting everything into Index `[0]`). |

*Note: Rehashing a table takes $O(N)$ computational time but occurs extraordinarily rarely, maintaining the overall amortized constant time of $O(1)$.*
