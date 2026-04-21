# CSCI240 Mock Exam: Answer Keys & Explanations
*DO NOT LOOK UNTIL YOU HAVE ATTEMPTED THE QUIZZES.*

<br><br><br><br><br><br><br><br><br><br>

---

### Quiz 1: Linear Data Structures
**1. D ($O(n^2)$)**. In theoretical Big-O modeling, we completely shed coefficients ($3$) and entirely drop lower-tier growth models ($50n$ and $\log n$). Only the fastest growing, highest polynomial bounding dimension remains: $n^2$.
**2. C (Stack)**. Stacks are mathematically forced perfectly into a strictly LIFO architecture. 
**3. C ($O(n)$)**. If you lack a pointer aiming at the `tail`, the only path the CPU has to attach a node to the backside is walking the disjoint memory completely from the `head` across $N$ elements linearly.
**4. C (`index = (index + 1) % N`)**. The Modulo physics forces variables strictly into boundary cycling. If $N = 100$ and your index is $99$, `(99 + 1) % 100` drops the pointer identically back to $0$.

**5. Memory Leak Danger**: Linked nodes inherently only "know" the position of the immediate `next` item. Removing or overwriting your solitary pointer to `Head` severs the only mathematical bridge to the collection. The JVM's **Garbage Collector** detects these now orphaned memory islands and wipes them to prevent memory leaking.
**6. Positional Abstraction:** A raw array using `.add(index)` must force the CPU to linearly push every single sub-element $+1$ spaces laterally to clear room (taking $O(N)$). Returning an abstraction like `Position` isolates inserting completely: it points to exact node memory addresses, so inserting merely requires swapping three pointer references `(node.next = new...)` regardless of million-tier scales, dropping time complexity instantly to $O(1)$.

---

### Quiz 2: Hierarchical Structures
**1. C ($O(n)$)**. In an explicitly *Unsorted* array PQ, the actual `insert()` is instant $O(1)$ at the back of the line. But locating the absolute smallest item forces the program to linearly scan out every $N$ slot comparing them individually.
**2. B**. The fundamental architectural rule of a Min-Heap. Parents strictly are equal or lower to children.
**3. B (`(i - 1) / 2`)**. Array index math maps tree hierarchies cleanly without pointers.
**4. C (In-Order Evaluation)**. Processing the `left` (smaller items), then `root` (middle), then `right` (larger items).

**5. Upheap vs Downheap**: Inserting pushes the new variable physically against the back tail array index. Since it is the absolute "smallest" item, it violates the Min-Heap parent relationship, meaning it must bubble heavily upward towards the root node. This causes an **Upheap** mechanism executing in $O(\log n)$ constraints.
**6. Comparator Analysis**: The exact purpose of a Comparator overrides native sorting limits allowing humans to dictate priority logic. If an interface expects `compare(a, b)`, overriding logic returning `b.compareTo(a)` flips alphabetical analysis backward, converting default `A-Z` logic strictly into pulling `Z-A`.

---

### Quiz 3: Hash Maps
**1. B (Amortized $O(1)$ expected)**. Guaranteed $O(1)$ is statistically impossible because theoretically a hundred malicious inputs could evaluate causing a huge hash stringing chain, generating an $O(N)$ search. But probabilistically over broad spaces, it balances into general Amortized $O(1)$.
**2. C (Entries / Capacity)**.
**3. D**. Separate Chaining creates discrete independent buckets attached to each array slot. It chains the collided items smoothly downwards ensuring zero overwrites or complex forward probing.
**4. B**. Bitshift zero `<< 0` retains identical binary bits mirroring static addition sequences. Thus "pot", "top", and "opt" would return the mathematically identical hash sequence index resulting in widespread overlapping collisions.

**5. Rehashing Mechanics**: A Rehash triggers absolutely dynamically the instant the `Load Factor` ratio threshold (traditionally $0.75$) gets breached. It requires $O(N)$ taxation because it forces the application to completely allocate an arraysize exactly twice the size, forcing everything to completely restart mathematically translating and moving original array contents onto the new expanded table memory. 
**6. The MAD Compressor**: Multiplying variables against complex random Primes creates heavy disruption inside mathematical properties ensuring generated values cleanly scatter randomly against array walls, avoiding linear cluster formations.
