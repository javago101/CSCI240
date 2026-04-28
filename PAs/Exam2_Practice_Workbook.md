# CSCI240 Exam 2: Practice Workbook (Exercises & Solutions)

## Part 0: The "Plain English" Intuition Guide
If the code looks like "heavenly script" (gibberish), use these metaphors to understand the **logic** before you look at the **math**.

### 1. Hash Maps: The "Magic Locker"
*   **The Idea**: You have 100 lockers. You don't want to check them one by one.
*   **The Hash Function**: A "magic formula." You give it your key, and it instantly says: "Go to Locker #14."
*   **Collision**: Two different keys point to the same locker (#14).
*   **Linear Probing**: Locker #14 is full? Just check #15, then #16... walk down the hallway until you find an empty spot.
*   **DEFUNCT**: Imagine you take your stuff out of Locker #14. You must put a sign that says **"WAS FULL, NOW EMPTY. KEEP WALKING!"** If you leave it totally blank (`null`), a person looking for stuff in #15 might stop at #14 and think the whole hallway is empty.

### 2. Heaps: "King of the Hill"
*   **The Idea**: Only the "Boss" (minimum or maximum value) matters. The Boss is always at the top.
*   **Up-heap (Insert)**: A new person joins the team. They compare themselves to their Boss. If they are "better" (smaller in a Min-Heap), they swap places. They keep moving up until they meet a Boss they can't beat.
*   **Down-heap (Remove)**: The Boss leaves. We grab a random person from the bottom to sit in the Boss's chair. This person is likely "weak," so they compare themselves to their two assistants. The "better" assistant takes the chair, and the weak person moves down one level. This repeats until everyone is in the right place.

### 3. AVL Trees: The "Balanced Mobile"
*   **The Idea**: If a tree gets too long on one side, it becomes slow to search.
*   **Rotation**: Think of a hanging mobile. If the left side is too heavy, you grab the "string" at the middle node and pull it up. The heavy part swings down to the other side.
*   **The Goal**: Keep the tree "short and fat" so you can find any node in very few steps.

### 4. BST: "Left is Less, Right is More"
*   **The Idea**: A simple sorting rule. "Small people to the left, tall people to the right."
*   **Successor (Deletion)**: You want to remove a person who has children. Who takes their place? You look at the "tall group" (right side) and find the **shortest person** in that group. That person is the perfect replacement because they are still taller than everyone on the left, but shorter than everyone else on the right.

---

## Part 1: Hash Tables (The Probing Gauntlet)

**Scenario**: You have a Hash Table of size **7**. The hash function is $h(k) = k \mod 7$.
**Data Sequence**: `[10, 24, 3, 17, 31]`

### Exercise 1.1: Linear Probing
1.  Insert 10: `10 % 7 = 3` -> Index 3
2.  Insert 24: `24 % 7 = 3` -> **Collision!** Check Index 4 (Empty).
3.  Insert 3: `3 % 7 = 3` -> **Collision!** Check Index 4 (Full), Check Index 5 (Empty).
4.  Insert 17: `17 % 7 = 3` -> **Collision!** Check Index 4, 5, 6 (Empty).
5.  Insert 31: `31 % 7 = 3` -> **Collision!** Check Index 4, 5, 6, 0 (Empty).

**[Solution Key - Array State]**:
`[31, null, null, 10, 24, 3, 17]`

### Exercise 1.2: Double Hashing
**Secondary Hash**: $h'(k) = 5 - (k \mod 5)$.
**Formula**: $Index = (h(k) + i \cdot h'(k)) \mod 7$.

1.  Insert 10: `10 % 7 = 3`.
2.  Insert 24: `24 % 7 = 3`. **Collision!**
    *   $h'(24) = 5 - (24 \mod 5) = 5 - 4 = 1$.
    *   Next: $(3 + 1 \cdot 1) \mod 7 = 4$.

**[Question]**: Why is Double Hashing better than Linear Probing?
**[Answer]**: It avoids **Primary Clustering**. Even if two keys have the same primary hash, they likely have different secondary hashes, scattering them across the table.

---

## Part 2: AVL Trees (The Rotation Architect)

**Scenario**: Build an AVL tree by inserting: `[30, 20, 10, 40, 50, 35]`

### Step-by-Step Trace:
1.  **Insert 30, 20**: Simple BST.
2.  **Insert 10**: Tree is `30 -> 20 -> 10` (Left-Left heavy at root 30).
    *   **Action**: **RR Rotation** (Single Right) at node 30.
    *   **[Solution Key - Tree State]**:
        ```text
            20
           /  \
          10   30
        ```
3.  **Insert 40, 50**: Node 30 becomes Right-Right heavy.
    *   **Action**: **LL Rotation** (Single Left) at node 30.
    *   **[Solution Key - Tree State]**:
        ```text
            20
           /  \
          10   40
              /  \
             30   50
        ```
4.  **Insert 35**: Imbalance detected at root **20**.
    *   Path: `20 (Right) -> 40 (Left) -> 30 (Right)`. This is an **RL (Right-Left)** case.
    *   **Action**: **Double Rotation** (First LL on 30/35, then RR on 40/35... effectively making 30 the new center).
    *   **[Solution Key - Final Tree State]**:
        ```text
              30
             /  \
            20   40
           /    /  \
          10   35   50
        ```

---

## Part 3: Heaps (The Priority Sorter)

**Exercise**: Start with a Min-Heap `[2, 5, 10, 8, 12, 15]`.
1.  Execute `removeMin()`.
2.  **Trace**:
    *   Root `2` is removed.
    *   Last element `15` moves to root: `[15, 5, 10, 8, 12]`.
    *   **Down-heap**: Compare 15 with children 5 and 10.
    *   Swap with **smaller** child (5).
    *   `[5, 15, 10, 8, 12]`.
    *   **Down-heap again**: Compare 15 with children 8 and 12.
    *   Swap with 8.
    *   Final: `[5, 8, 10, 15, 12]`.

---

## Part 4: Short Answer Logic

1.  **Q**: Why is `remove` in a Priority Queue (implemented as a Heap) $O(\log n)$?
    **A**: Because you must move the last element to the root and perform "Down-heap" bubbling, which travels at most the height of the tree ($O(\log n)$).

2.  **Q**: In a Hash Table with Linear Probing, if we have $N$ slots and $N-1$ are full, what is the complexity of a *failed* search?
    **A**: $O(N)$, because you might have to scan almost the entire array before hitting a `null` slot.
---

## Part 5: Algorithm Reference (Pseudocode & Java)

### 5.1 Heap: Down-heap Bubbling
Used to restore the heap property after `removeMin()`.

**Java Implementation**:
```java
protected void downheap(int j) {
    while (hasLeft(j)) {               // Continue as long as there is a child
        int leftIndex = left(j);
        int smallChildIndex = leftIndex;
        if (hasRight(j)) {
            int rightIndex = right(j);
            if (compare(data.get(leftIndex), data.get(rightIndex)) > 0)
                smallChildIndex = rightIndex; // Pick the smaller child
        }
        // If parent is already smaller than smallest child, property is restored
        if (compare(data.get(smallChildIndex), data.get(j)) >= 0)
            break; 
        swap(j, smallChildIndex);      // Otherwise, swap and continue down
        j = smallChildIndex;
    }
}
```

### 5.2 AVL Tree: Right Rotation (Single Rotation)
Used to balance a Left-Left heavy tree.

**Java Implementation**:
```java
protected Node<E> rotateRight(Node<E> z) {
    Node<E> y = z.getLeft();
    z.setLeft(y.getRight());            // Move y's right child to z's left
    if (y.getRight() != null)
        y.getRight().setParent(z);
    y.setRight(z);                      // Make z the right child of y
    y.setParent(z.getParent());         // Update parent pointers
    z.setParent(y);
    updateHeight(z);                    // Update bottom node first
    updateHeight(y);                    // Update top node last
    return y;
}
```

### 5.3 Hash Map: Linear Probing Search
Handles the logic of skipping `DEFUNCT` slots.

**Pseudocode**:
```text
Algorithm findSlot(k):
    h = hash(k)
    for i from 0 to capacity - 1:
        j = (h + i) % capacity
        if table[j] is null:
            return -1                // Key definitely not in map
        if table[j].key == k:
            return j                 // Found key at index j
        // If table[j] is DEFUNCT, loop continues to skip it
```

### 5.4 BST: Recursive Removal
Demonstrates the successor logic for a two-child case.

**Pseudocode**:
```text
Algorithm remove(k, node):
    if node is null: return null
    if k < node.key: 
        node.left = remove(k, node.left)
    else if k > node.key: 
        node.right = remove(k, node.right)
    else: // Key found!
        if node has two children:
            s = findMin(node.right)  // Find in-order successor
            node.key = s.key         // Replace current value with successor's
            node.right = remove(s.key, node.right) // Delete the successor node
        else:
            // Node has 0 or 1 child: replace with non-null child
            node = (node.left != null) ? node.left : node.right
    return node
```

---

## Part 6: Comprehensive Java Implementation

### 6.1 Heap: Full Insertion & Removal Logic
This covers how the array grows and how the property is maintained.

```java
public void insert(E element) {
    data.add(element);                  // Step 1: Add to the end of the array list
    upheap(data.size() - 1);            // Step 2: Bubble up to restore heap property
}

protected void upheap(int j) {
    while (j > 0) {
        int p = parent(j);
        // If current node is >= parent, Min-Heap property is satisfied
        if (compare(data.get(j), data.get(p)) >= 0) break; 
        swap(j, p);
        j = p;
    }
}

public E removeMin() {
    if (data.isEmpty()) return null;
    E answer = data.get(0);             // The root is the minimum
    swap(0, data.size() - 1);           // Put the last element at the root
    data.remove(data.size() - 1);       // Remove the old minimum
    downheap(0);                        // Bubble down to restore property
    return answer;
}
```

### 6.2 AVL Tree: Restructure & Left Rotation
The `restructure` method is the high-level logic that decides which rotation to perform.

```java
// Logic for LL (Single Left) Rotation
protected Node<E> rotateLeft(Node<E> z) {
    Node<E> y = z.getRight();
    z.setRight(y.getLeft());            // Move y's left child to z's right
    if (y.getLeft() != null)
        y.getLeft().setParent(z);
    y.setLeft(z);                       // Make z the left child of y
    y.setParent(z.getParent());
    z.setParent(y);
    updateHeight(z);
    updateHeight(y);
    return y;
}

// Logic to identify LL, RR, LR, RL cases
protected Node<E> restructure(Node<E> z) {
    Node<E> y = tallestChild(z);
    Node<E> x = tallestChild(y);
    if ((y == z.getLeft()) && (x == y.getLeft())) 
        return rotateRight(z);          // Case LL -> Single Right Rotation
    if ((y == z.getRight()) && (x == y.getRight())) 
        return rotateLeft(z);           // Case RR -> Single Left Rotation
    if (y == z.getLeft()) {
        rotateLeft(y);                  // Case LR -> Double Rotation
        return rotateRight(z);
    } else {
        rotateRight(y);                 // Case RL -> Double Rotation
        return rotateLeft(z);
    }
}
```

### 6.3 Hash Map: Linear Probing Java Implementation
Focus on how `DEFUNCT` is handled during `put` vs `get`.

```java
private int findSlot(int h, K k) {
    int avail = -1;                     // Index of first DEFUNCT or null slot
    int j = h;
    do {
        if (isAvailable(j)) {           // DEFUNCT or null
            if (avail == -1) avail = j; // Remember the first available slot
            if (table[j] == null) break;// Stop search if slot is null
        } else if (table[j].getKey().equals(k)) {
            return j;                   // Found the key!
        }
        j = (j + 1) % capacity;         // Linear probing step
    } while (j != h);                   // Stop if we circled back to start
    return -(avail + 1);                // Not found; return encoded available slot
}
```

