# CSCI240 Mock Exam: Quiz 3
**Topic Module**: Hash Tables, Hash Functions, MAD, Load Factors (PA7)

---

### Section 1: Multiple Choice
*Hint: Pay close attention to definitions of collisions and runtime costs.*

**1. In PA7, you implemented a Hash Map designed to execute insertions in heavily optimized timing bounds. Assuming a balanced hash function and a secure Load Factor, what is the standard expected Time Complexity for inserting an item into a Hash Map?**
a) Absolute $O(1)$ constant time (Guarantee)
b) Amortized $O(1)$ constant time (Expected)
c) $O(\log n)$ logarithmic time
d) $O(n)$ linear time

**2. The equation for the "Load Factor" ($\lambda$) in standard Java architecture is universally represented as:**
a) `Capacity / Entries`
b) `(Capacity - Entries) / Prime`
c) `Entries / Capacity`
d) `2 * Capacity / Entries`

**3. In a `ChainHashMap` (Separate Chaining), what specifically happens when two distinct keys evaluate to the exact same Bucket Index inside the underlying generic array?**
a) The newly processed key overwrites the old key.
b) An `IndexOutOfBoundsException` is instantly thrown.
c) The map searches adjacent indexes (+1, +2) forward until it locates empty memory.
d) The new entry is appended to a sub-list (node chain) living locally inside that specific bucket.

**4. A Cyclic-Shift hash attempts to scramble sequence inputs to reduce collisions across large vocabulary arrays (like parsing the US Constitution). In PA7, testing determined that setting the Shift parameter directly to '0' causes massive collision spikes. Why mathematically does `shift = 0` destroy the Hash?**
a) Shifting by zero triggers a computational division-by-zero math trap in the CPU.
b) Shifting zero bits leaves bit sequence properties completely untouched, degrading identically to simple static ASCII character addition (e.g., "pot" == "top").
c) Java forbids bitwise shifting parameters below 1.
d) A zero shift forces the table capacity down to zero triggering instant Rehashing.

---

### Section 2: Code Conceptualization
**5. Rehashing Mechanics:** Outline exactly what physical events trigger the underlying `Rehash()` function in a generic Hash Map. Additionally, explain what processing payload makes Rehashing a highly expensive $O(N)$ tax compared to a regular insert.

**6. The MAD Compressor:** The MAD (Multiply-Add-Divide) compression formula is visually structured as: `[(a * hashCode + b) % p] % N`. Provide a one-sentence definition explaining the exact purpose why we introduce highly specialized logic like $p$ (Random Prime Number) simply to choose an array slot!
