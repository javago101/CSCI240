# CSCI240 Exam 2: The Ultimate Workbook & Study Guide

This document is designed to be your primary study companion. It balances high-level theory with the "nitty-gritty" details required for manual traces and implementation questions.

---

## 1. Hash Tables (PA7): The Mechanics of $O(1)$

### 1.1 The Theoretical Trade-off
Hash Tables achieve $O(1)$ speed by sacrificing memory and order. The core engine is the **Hash Function**:
`Index = [(a * hashCode + b) % Prime] % Capacity` (The MAD Method).

### 1.2 Workbook: Manual Trace Exercise
**Scenario**: Insert keys `[12, 25, 45, 14, 1, 21]` into a table of size **7** using $h(k) = k \mod 7$.

#### Technique A: Linear Probing
1.  **Insert 12**: `12 % 7 = 5` -> Slot 5: **[12]**
2.  **Insert 25**: `25 % 7 = 4` -> Slot 4: **[25]**
3.  **Insert 45**: `45 % 7 = 3` -> Slot 3: **[45]**
4.  **Insert 14**: `14 % 7 = 0` -> Slot 0: **[14]**
5.  **Insert 1**: `1 % 7 = 1` -> Slot 1: **[1]**
6.  **Insert 21**: `21 % 7 = 0` -> **Collision!** 
    *   Check Slot 1: Occupied.
    *   Check Slot 2: Empty.
    *   Result -> Slot 2: **[21]**
    *   *Exam Stat*: This insertion took **3 probes** (0, 1, 2).

#### Technique B: Separate Chaining
1.  Slot 0: `[14 -> 21]` (Linked List)
2.  Slot 1: `[1]`
3.  Slot 3: `[45]`
4.  Slot 4: `[25]`
5.  Slot 5: `[12]`

### 1.3 Implementation Deep Dive: The `DEFUNCT` Sentinel
**Problem**: If you delete "14" in Linear Probing by setting index 0 to `null`, a search for "21" will stop at index 0 and return "Not Found".
**Solution**: Set index 0 to `DEFUNCT`. The search algorithm treats `DEFUNCT` as "keep looking," while the `put` algorithm treats it as "available to overwrite."

---

## 2. Heaps & Priority Queues (PA6): Hierarchical Efficiency

### 2.1 The Array Representation
A Heap is a **Complete Binary Tree**. In an array `A`:
*   `Left(i)` = `2i + 1`
*   `Right(i)` = `2i + 2`
*   `Parent(i)` = `(i - 1) / 2`

### 2.2 Workbook: Step-by-Step Bubbling
**Task**: Build a Min-Heap by inserting `[10, 5, 15, 2]`.

1.  **Insert 10**: `[10]`
2.  **Insert 5**: `[10, 5]` -> **Up-heap**: 5 is smaller than parent 10. **Swap**.
    *   Result: `[5, 10]`
3.  **Insert 15**: `[5, 10, 15]` -> 15 > parent 5. No swap.
4.  **Insert 2**: `[5, 10, 15, 2]` -> **Up-heap**: 2 < parent 10. **Swap**.
    *   Array: `[5, 2, 15, 10]`
    *   **Up-heap again**: 2 < parent 5. **Swap**.
    *   Final: `[2, 5, 15, 10]`

### 2.3 Exam Strategy: `removeMin()`
1.  Replace the root with the **last element** in the array.
2.  Perform **Down-heap** bubbling: Compare parent with **both** children; swap with the **smaller** child to maintain the Min-Heap property.

---

## 3. Binary Search Trees (BST): The Successor Logic

### 3.1 The Order Property
`Left Child < Root < Right Child`. 
*   An **In-order Traversal** of a BST is always a sorted sequence.

### 3.2 Workbook: Deletion with Two Children
**Task**: Delete node `[15]` from a tree where 15 has children.
1.  Find the **In-order Successor**: Go to the right child, then go as far left as possible (the minimum value in the right subtree).
2.  Copy the successor's value to the node being deleted.
3.  Delete the original successor node (which is guaranteed to have at most one child).

---

## 4. AVL Trees: Guaranteed Performance

### 4.1 The Balance Factor (BF)
`BF(n) = Height(Left) - Height(Right)`
*   Balanced if $BF \in \{-1, 0, 1\}$.

### 4.2 Workbook: The Four Rotations
Imbalance occurs at node `z` (the first node with $|BF| > 1$ while walking up from the insertion point).

1.  **LL (Right Rotation)**: `y` is left child of `z`, `x` is left child of `y`.
    *   *Action*: Pull `y` up, `z` becomes `y`'s right child.
2.  **RR (Left Rotation)**: `y` is right child of `z`, `x` is right child of `y`.
    *   *Action*: Pull `y` up, `z` becomes `y`'s left child.
3.  **LR (Double Rotation)**: `y` is left child of `z`, `x` is right child of `y`.
    *   *Action*: Rotate `x` and `y` (RR/Left), then rotate `x` and `z` (LL/Right).
4.  **RL (Double Rotation)**: `y` is right child of `z`, `x` is left child of `y`.
    *   *Action*: Rotate `x` and `y` (LL/Right), then rotate `x` and `z` (RR/Left).

---

## 5. Summary Cheat Sheet for Exam Day

| Structure | Best Search | Worst Search | Key Property |
| :--- | :--- | :--- | :--- |
| **Array** | $O(1)$ | $O(n)$ | Continuous memory |
| **Hash Table** | $O(1)$ | $O(n)$ | Unordered, Fast |
| **BST** | $O(\log n)$ | $O(n)$ | Ordered |
| **AVL Tree** | $O(\log n)$ | $O(\log n)$ | Guaranteed Balance |
| **Min-Heap** | $O(n)$* | $O(n)$ | Root is minimum |

*\*Heaps are not optimized for searching specific keys.*

### Conceptual Checklist
- [ ] Why is $O(1)$ search in HashMaps only "amortized"? (Because of Resize/Rehash).
- [ ] What is the difference between Depth and Height? (Depth is from root down; Height is from leaves up).
- [ ] When is a BST better than a Hash Map? (When you need range queries like "find all keys between 10 and 50").
