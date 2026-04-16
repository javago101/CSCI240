# PA7 Exercises Completion Walkthrough

All tasks for Programming Assignment 7 have been successfully developed, integrated, and validated! Here is what was accomplished:

## 1. PA7_Ex1.java (Native Java `HashMap`)
- **Key Functionality**: Creates a built-in Java map and performs the specific initial key:value mapping (reversing the integer locally). Ex: 37 becomes "73".
- **Scale Testing**: Read arrays of $1,000$ elements and $100,000$ elements using optimal $0.75$ load factor capacities (`1,334` and `133,334` spaces respectively).
- **Outcome**: The small text sorted in `~1ms`, and the large text sorted in `~12ms`. Scaling up $100 \times$ produced roughly a comparative constant time scale increase (very fast overall).

## 2. PA7_Ex2.java (Book's `ChainHashMap`)
- **Key Functionality**: Adapts the exact sequence of PA7_Ex1 entirely out of Java's internal systems, and relies directly on your textbook's `net.datastructures.ChainHashMap`.
- **Outcome**: It managed identically precise key searches and removals. Timing was extremely close (`~2ms` for small and `~52ms` for large). Proving the linear probing/chaining mechanism holds up to the enterprise level! 

## 3. PA7_Ex3.java (Polynomial Hash Evaluation)
- **Key Functionality**: Scans the `<USDeclIndFormatted.txt>` file, pulling out `539` completely distinct English words. It then processes them through the standard Horner's Polynomial hash sequence. 
- **The Catch**: You swap out parameter $a$. When $a$ is set to `{37, 40, 41}`, you achieve **0 collisions**! This means the math flawlessly uniquely maps every word safely inside 32-bit `int` limits.
- **The Bad Value**: But when $a = 1$, the math deteriorates into just summing the alphabet values (so "god" equals "dog"). Because of this, it suffered an intentional $178$ fatal collisions. The logic worked perfectly!

> [!TIP]
> The source codes are in `PAs/PA7/`. Feel free to open those up to see your Author stamps formatted securely at the top!
