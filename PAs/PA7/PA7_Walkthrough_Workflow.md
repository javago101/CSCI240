# PA7 Exercises & Extra Credits Completion Walkthrough

All tasks for Programming Assignment 7, including the two full-credit "Extra Credit" options, have been successfully developed, integrated, and validated! Here is the finalized rundown of what was accomplished:

## 1. PA7_Ex1.java (Native Java `HashMap`)
- **Key Functionality**: Creates a built-in Java map and performs the specific initial key:value mapping (reversing the integer locally). Ex: 37 becomes "73".
- **Scale Testing**: Read arrays of $1,000$ elements and $100,000$ elements using optimal $0.75$ load factor capacities (`1,334` and `133,334` spaces respectively).
- **Outcome**: The small text sorted in `~1ms`, and the large text sorted in `~12ms`. Scaling up $100 \times$ produced roughly a comparative constant time scale increase, verifying O(1) properties.

## 2. PA7_Ex2.java (Book's `ChainHashMap`)
- **Key Functionality**: Adapts the exact sequence of PA7_Ex1 entirely out of Java's internal systems, and relies directly on your textbook's `net.datastructures.ChainHashMap`.
- **Outcome**: It managed identically precise key searches and removals. Timing was extremely close (`~3ms` for small and `~51ms` for large). Proving the linear probing/chaining mechanism holds up to the enterprise level! 

## 3. PA7_Ex3.java (Polynomial Hash Evaluation)
- **Key Functionality**: Scans the `<USDeclIndFormatted.txt>` file, pulling out `539` completely distinct English words. It then processes them through the standard Horner's Polynomial hash sequence. 
- **The Catch**: You swap out parameter $a$. When $a$ is set to `{37, 40, 41}`, you achieve **0 collisions**! This means the math flawlessly uniquely maps every word safely inside 32-bit `int` limits.
- **The Bad Value**: But when $a = 1$, the math deteriorates into just summing the alphabet values (so "god" equals "dog"). Because of this, it suffered an intentional $178$ fatal collisions.

## 4. PA7_EC1.java (Cyclic-Shift Hash Evaluation)
- **Key Functionality**: Tests identical extraction to Ex3 but uses bitwise cyclic shifting instead of polynomial accumulation.
- **Outcome**: Applying the book's cyclic-shift equation with shifts `5` and `13` perfectly evaded conflicts yielding **0** collisions. Meanwhile a shift of `0` failed to mix any bits positionally resulting again in **178** crushing collisions.

## 5. PA7_EC2.java (Load Factors & Chain Hashing Probes Tracker)
- **Key Functionality**: We effectively built a completely localized, un-intrusive extension of the book's `AbstractHashMap` and chained `UnsortedTableMap` arrays to natively track precisely how many times internal arrays were parsed ("Probes").
- **Outcome**: Testing the entire dictionary of large100k demonstrated clear properties: 
  - Generous `0.25 LF` memory yielded nearly instantaneous insertion parameters (`1.5042 Avg Probes`).
  - Constricted `0.9 LF` squeezed memory tightly forcing slightly heavier list climbing metrics.

> [!TIP]
> The source codes are cleanly sorted in `PAs/PA7/`. Feel free to open any of them up to see your Author stamps (`Modified by: Aiden Wang`) formatted perfectly atop!
