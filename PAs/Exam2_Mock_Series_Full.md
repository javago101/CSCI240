# CSCI240 Exam 2: Mock Exam Series (5 Sets)

This series contains 5 sets of practice exams, each following the 5-5-9 format (5 T/F, 5 Multiple Choice, 9 Short Answer/Problem Solving).

---

## 🟢 Set 1: Foundation & Basics
**Focus**: Definitions, basic traces, and property verification.

### Part 1: True/False
1. [ ] A Min-Heap of height $h$ always has exactly $2^h$ nodes.
2. [ ] In an AVL tree, the height of the left and right subtrees of any node can differ by at most 1.
3. [ ] Separate Chaining (using Linked Lists) is a type of Open Addressing collision resolution.
4. [ ] The `removeMin` operation in a Heap takes $O(\log N)$ time in the worst case.
5. [ ] A BST's In-order traversal always produces the keys in sorted order.

### Part 2: Multiple Choice
1. If a node in a Binary Tree is at index $i$ in an array-based representation (starting at 0), its **right child** is at index:
   A) $2i$  B) $2i + 1$  C) $2i + 2$  D) $i/2$
2. Which collision resolution method requires the use of a `DEFUNCT` (or "available") marker when an item is deleted?
   A) Separate Chaining  B) Linear Probing  C) External Hashing  D) None of the above
3. What is the maximum height of an AVL tree with $N$ nodes?
   A) $O(\sqrt{N})$  B) $O(\log N)$  C) $O(N)$  D) $O(N \log N)$
4. In a Min-Heap, where is the second-smallest element located?
   A) Always the right child of the root.  B) Either the left or right child of the root.  C) Any leaf node.  D) The last node in the array.
5. A hash table has capacity 10. After inserting 8 elements, the **load factor ($\alpha$)** is:
   A) 0.1  B) 0.8  C) 1.25  D) 8.0

### Part 3: Short Answer & Problem Solving
1. **Hash Map Trace**: Insert `[15, 22, 29, 8]` into a table of size 7 using $h(k) = k \mod 7$ and **Linear Probing**.
2. **Heap Trace**: Draw the resulting Min-Heap after inserting `[5, 10, 2, 8]` one by one.
3. **AVL Identification**: Given a tree where the root has left-height 3 and right-height 1, identify the balance factor and state if it is an AVL tree.
4. **BST Logic**: In a BST, if you delete a node with two children, what are the two possible replacement nodes?
5. **Complexity**: What is the worst-case time complexity of searching for a key in a non-balanced BST?
6. **Heap Logic**: Why is a Heap typically implemented using an Array/ArrayList instead of a linked structure?
7. **Compression**: Explain the difference between "Hash Code" and "Compression Function."
8. **Traversals**: Provide the Pre-order traversal of a tree with root A, left child B, and right child C.
9. **AVL Rotation**: Draw the result of a Single Right Rotation on a path 30-20-10.

---

## 🟡 Set 2: Balancing & Deletions
**Focus**: Complex AVL cases and tree maintenance.

### Part 1: True/False
1. [ ] An AVL tree is a specialized version of a Binary Search Tree.
2. [ ] Deleting a node from an AVL tree may require more than one rotation to restore balance.
3. [ ] A Heap is always a "Complete Binary Tree."
4. [ ] Double Hashing is used to eliminate "Primary Clustering."
5. [ ] In a Max-Heap, the smallest element must be a leaf node.

### Part 2: Multiple Choice
1. After a `removeMin` in a heap, which node is moved to the root to start the `downheap` process?
   A) The left child of the root  B) The last node in the level-order traversal  C) The smallest leaf  D) The parent of the last node
2. Which traversal is used to compute the space used by a directory (including all subdirectories)?
   A) Pre-order  B) In-order  C) Post-order  D) Level-order
3. A BST has 10, 20, 30 inserted in that order. What is its current height?
   A) 1  B) 2  C) 3  D) 0
4. Which rotation is needed if a node's left-child's right-subtree is too tall?
   A) Single Right  B) Single Left  C) Left-Right Double  D) Right-Left Double
5. If the load factor $\alpha$ of a hash table exceeds 1.0, which resolution method MUST be in use?
   A) Linear Probing  B) Double Hashing  C) Separate Chaining  D) Quadratic Probing

### Part 3: Short Answer & Problem Solving
1. **AVL Trace**: Show the double rotation for inserting `[30, 10, 20]`.
2. **BST Deletion**: Draw the tree after deleting `40` from a tree where `40` has children `30` (left) and `50` (right).
3. **Heap removal**: Trace `removeMin` on a heap with array `[2, 5, 8, 10, 12]`.
4. **Hashing**: Define "Primary Clustering" in your own words.
5. **Tree heights**: What is the height of a single-node tree?
6. **BFS**: List the nodes of a full binary tree with 7 nodes in level-order.
7. **AVL Properties**: Can an AVL tree have a node with balance factor -1?
8. **Binary Trees**: How many leaves are in a full binary tree with $N$ nodes?
9. **Implementation**: What happens to the `DEFUNCT` slot during a `get(k)` operation?

---

## 🟠 Set 3: Hash Maps & Collisions
**Focus**: Probing logic and MAD method.

### Part 1: True/False
1. [ ] Linear Probing always finds an empty slot if the table is not full.
2. [ ] The MAD method helps reduce collisions compared to simple modulo.
3. [ ] A Heap is a type of Search Tree.
4. [ ] An In-order traversal of a Max-Heap produces sorted results.
5. [ ] AVL trees have $O(1)$ search time.

### Part 2: Multiple Choice
1. Secondary hashing $h'(k)$ in Double Hashing should never return:
   A) 0  B) 1  C) A prime number  D) A large number
2. The number of nodes in a complete binary tree of height $h$:
   A) $2h$  B) $2^{h+1}-1$  C) Between $2^h$ and $2^{h+1}-1$  D) $h^2$
3. Which structure is best for a "First-In, First-Out" Priority Queue?
   A) Stack  B) Heap  C) BST  D) Linked List
4. In Linear Probing, if $h(k)=5$ and slot 5, 6, 7 are full, where is the item placed?
   A) 8  B) 4  C) 0  D) It fails
5. What is the Big-O for `restructure()` in an AVL tree?
   A) $O(1)$  B) $O(\log N)$  C) $O(N)$  D) $O(N^2)$

### Part 3: Short Answer & Problem Solving
1. **Double Hashing Trace**: $h(k) = k \mod 7$, $h'(k) = 5 - (k \mod 5)$. Insert `[8, 15]`.
2. **Complexity Table**: List search time for Sorted Array vs AVL vs Hash Map.
3. **Euler Tour**: Draw a tree and mark the Post-order dots.
4. **Heap indices**: If a node is at index 10, where is its parent?
5. **Hash Codes**: Why is "polynomial hash code" better than "summing ASCII"?
6. **AVL Logic**: Is every complete binary tree an AVL tree?
7. **BST Logic**: Draw a BST that is NOT an AVL tree.
8. **Level-order**: Why do we use a Queue for BFS?
9. **Heaps**: Show a swap during `upheap`.

---

## 🔵 Set 4: Heaps & Priority Queues
**Focus**: Array mapping and bubbling logic.

### Part 1: True/False
1. [ ] Up-heap is used during `removeMin`.
2. [ ] Down-heap is used during `insert`.
3. [ ] A Heap can store duplicate values.
4. [ ] The root of a Min-Heap is always at index 0.
5. [ ] Hash tables maintain the relative order of elements.

### Part 2: Multiple Choice
1. Time complexity to convert an unsorted array to a Heap using `bottom-up` construction:
   A) $O(1)$  B) $O(N)$  C) $O(N \log N)$  D) $O(N^2)$
2. If a Heap array is `[3, 8, 10, 15]`, what is the root?
   A) 3  B) 15  C) 8  D) 10
3. Which rotation fixes an "outer" imbalance (e.g., left-left)?
   A) Single Right  B) Double Right  C) Single Left  D) Double Left
4. Search time in a Hash Map with a poor hash function (all items collide):
   A) $O(1)$  B) $O(\log N)$  C) $O(N)$  D) $O(N \log N)$
5. Minimum nodes in a binary tree of height 3:
   A) 3  B) 4  C) 7  D) 8

### Part 3: Short Answer & Problem Solving
1. **Heap construction**: Build a Max-Heap from `[1, 5, 10, 2, 8]`.
2. **Index calculation**: Find parent and children for index 4.
3. **AVL rotations**: Contrast LL and LR rotations.
4. **Hashing**: Explain the "Birthday Paradox" in terms of hash tables.
5. **BST Deletion**: What if the node to delete has only one child?
6. **BFS vs DFS**: Which uses more memory for a very "wide" tree?
7. **Priority Queues**: List 3 real-world examples.
8. **Heaps**: Why is the last node in the array important?
9. **MAD**: Calculate `[(3*7 + 4) % 11] % 7`.

---

## 🔴 Set 5: Comprehensive Finale
**Focus**: Mixed topics and time complexity.

### Part 1: True/False
1. [ ] A tree with 0 nodes has height -1.
2. [ ] Hash Maps are always faster than AVL trees for all operations.
3. [ ] In-order traversal visits nodes in descending order in a Max-Heap.
4. [ ] A double rotation is just two single rotations.
5. [ ] `DEFUNCT` slots are skipped during `put` but used during `get`. (Wait, think carefully!)

### Part 2: Multiple Choice
1. Worst-case height of a BST with $N$ nodes:
   A) $\log N$  B) $\sqrt{N}$  C) $N-1$  D) $N$
2. In a Hash Table using Linear Probing, deleting an item without `DEFUNCT` breaks:
   A) The hash code  B) The probing chain  C) The load factor  D) The universe
3. Best case time for `upheap`:
   A) $O(1)$  B) $O(\log N)$  C) $O(N)$  D) $O(N \log N)$
4. Which structure ensures $O(\log N)$ even in the worst case?
   A) BST  B) AVL  C) Hash Map  D) Linked List
5. Number of edges in a tree with $N$ nodes:
   A) $N$  B) $N+1$  C) $N-1$  D) $2N$

### Part 3: Short Answer & Problem Solving
1. **The Ultimate Trace**: Insert `[10, 20, 5, 15]` into an AVL.
2. **Comparison**: Why use a Heap instead of a Sorted Array for a Priority Queue?
3. **Hashing**: What is the "Amortized $O(1)$" concept?
4. **Traversals**: Convert `[A, B, C]` (Pre-order) to a tree.
5. **Heaps**: Draw a Min-Heap and its corresponding array.
6. **AVL**: Explain the height-balance property.
7. **BST**: Trace deleting the root from a 3-node balanced tree.
8. **Logic**: Why is `load factor < 0.5` recommended for Open Addressing?
9. **Final complexity**: Fill a $3 \times 3$ grid: BST vs AVL vs Heap search/insert/delete.

---

# 🏁 Answer Keys

## Set 1 Key
- **T/F**: 1:F, 2:T, 3:F, 4:T, 5:T
- **MC**: 1:C, 2:B, 3:B, 4:B, 5:B
- **SA**: (1) 15@1, 22@2, 29@3 (Linear probe), 8@4. (4) Successor or Predecessor. (5) $O(N)$.

## Set 2 Key
- **T/F**: 1:T, 2:T, 3:T, 4:T, 5:T
- **MC**: 1:B, 2:C, 3:B, 4:C, 5:C
- **SA**: (4) Clusters of occupied slots that slow down linear probing. (9) Treated as available for `put`, but `get` must continue past it.

## Set 3 Key
- **T/F**: 1:T, 2:T, 3:F, 4:F, 5:F
- **MC**: 1:A, 2:C, 3:B, 4:A, 5:A
- **SA**: (4) `(10-1)/2 = 4`. (5) Polynomial incorporates character positions.

## Set 4 Key
- **T/F**: 1:F, 2:F, 3:T, 4:T, 5:F
- **MC**: 1:B, 2:A, 3:A, 4:C, 5:B
- **SA**: (5) Just connect child to grandparent. (6) BFS uses more memory for wide trees (queue size).

## Set 5 Key
- **T/F**: 1:T, 2:F, 3:F, 4:T, 5:F (DEFUNCT used for put, skipped for get? No, get continues past, put replaces).
- **MC**: 1:C, 2:B, 3:A (if item > parent), 4:B, 5:C
- **SA**: (2) Heap has $O(\log N)$ insert; Sorted Array has $O(N)$ insert. (9) AVL is $O(\log N)$ for all. Heap is $O(1)$ findMin, $O(\log N)$ insert/delete.
