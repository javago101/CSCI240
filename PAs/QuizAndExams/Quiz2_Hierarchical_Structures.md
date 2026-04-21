# CSCI240 Mock Exam: Quiz 2
**Topic Module**: Trees, Hierarchies, Priority Queues, Binary Heaps (PA5 - PA6)

---

### Section 1: Multiple Choice
*Hint: Remember the difference between vanilla Trees, balanced Trees, and Heaps.*

**1. A professor gives you the following sequence of integers: `[50, 40, 20, 60, 10, 80]`. If placed directly into a basic Unsorted Priority Queue (an unsorted Array), what is the Big-O Time Complexity to locate and `extractMin()`?**
a) $O(1)$
b) $O(\log n)$
c) $O(n)$
d) $O(n^2)$

**2. Which of the following is an absolute structural requirement for an array-backed Binary Min-Heap?**
a) The left child must always be strictly less than the right child.
b) Every node's value must be $\le$ both of its children.
c) The root node perfectly represents the maximum value in the set.
d) The tree depth must equal $N$.

**3. In an array-backed Heap, the parent of the node situated at array index `i` is mathematically guaranteed to be found at which index?**
a) `(i + 1) / 2`
b) `(i - 1) / 2`
c) `2i + 1`
d) `2i + 2`

**4. You are performing a Depth-First Search (DFS) Traversal on a standard Binary Search Tree (BST) and want the output of your code to be printed in perfectly ascending numerically sorted order. Which traversal protocol must you use?**
a) Pre-Order Evaluation
b) Post-Order Evaluation
c) In-Order Evaluation
d) Breadth-First Sweep

---

### Section 2: Code Conceptualization
**5. Upheap vs Downheap:** In PA6, you implemented `HeapPriorityQueue`. If a user calls `.insert(key)` inserting the absolute smallest element into a Min-Heap, outline the mechanical sequence of steps the tree takes to restore its property. Does this action invoke an Upheap or Downheap bubble?

**6. Comparator Analysis:** Explain the role of the `Comparator<K>` interface in Java. If the default mechanism sorts `A` to `Z`, provide the logical code structure (`compare(String a, String b)`) representing how you could force a Priority Queue to extract `Z` first (Max-Heap behavior).
