# PA8 Theoretical Questions & Traces

## Question 1: BST Trace
**Operations:** Insert 10, 20, 4, 8, 15 -> Erase 8 -> Erase 10.

### Step-by-Step Evolution:
1.  **Insert 10, 20, 4**:
    ```
      10
     /  \
    4    20
    ```
2.  **Insert 8, 15**:
    ```
        10
       /  \
      4    20
       \   /
        8 15
    ```
3.  **Erase 8**: (Leaf node removed)
    ```
      10
     /  \
    4    20
        /
       15
    ```
4.  **Erase 10**: (Root with two children. Replaced by predecessor `4`)
    ```
      4
       \
        20
       /
      15
    ```

**Final BST Keys:** `4, 20, 15` (Tree: 4 -> Right: 20 -> Left: 15)

---

## Question 2: AVL Trace
**Operations:** Insert 10, 20, 30, 15, 12, 20 (update), Erase 30.

### Step-by-Step Evolution:
1.  **Insert 10, 20, 30**:
    - Initial: 10 -> 20 -> 30 (Imbalance at 10)
    - After Left Rotation: `20 (L:10, R:30)`
2.  **Insert 15, 12**:
    - Adding 15: `20 (L: 10(R:15), R: 30)`
    - Adding 12: `20 (L: 10(R:15(L:12)), R: 30)` (Imbalance at 10)
    - Double Rotation (Right-Left) at 10: `20 (L: 12(L:10, R:15), R: 30)`
3.  **Insert 20**: (Duplicate key, value updated, structure unchanged)
4.  **Erase 30**:
    - Initial: `20 (L: 12(L:10, R:15), R: null)` (Imbalance at 20)
    - Single Rotation at 20: `12` becomes root.
    - Final Map:
    ```
        12
       /  \
      10   20
          /
         15
    ```

**Final AVL Keys:** `12, 10, 20, 15`
