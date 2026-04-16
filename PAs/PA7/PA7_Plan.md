# PA7 Implementation Plan

This plan details the steps to complete all three exercises for PA7 based on your provided prompt. All code will strictly carry your `"Modified by: Aiden Wang"` tag and will utilize the datasets found in your `PAs/PA7/Data/` folder.

## User Review Required

Please review the breakdown below before I generate the code.

## Proposed Changes

---

### PA7_Ex1.java (Java `HashMap` Tests)
- **What it does**: 
  - Creates a `java.util.HashMap`.
  - Performs the 5 specific inserts (reversing the integer to create the string value) and evaluates the targeted searches (10, 21, 37) and removals (20, 37).
  - Uses an iterator (over `entrySet`) to print all elements.
  - Times the insertion duration for all $1,000$ values from `small1k.txt` into a new `HashMap` initialized with `capacity = 1000 / 0.75`.
  - Times the insertion duration for all $100,000$ values from `large100k.txt` into a new `HashMap` initialized with `capacity = 100000 / 0.75`.
  - Compares the speeds to demonstrate $O(1)$ amortized insertion behavior (where 100,000 roughly takes 100 times longer than 1,000).

### PA7_Ex2.java (Book's `ChainHashMap` Tests)
- **What it does**:
  - Exactly duplicates `PA7_Ex1.java` logic but swaps out Java's native map for the textbook's custom `net.datastructures.ChainHashMap`.
  - Uses capacity 11 (`new ChainHashMap<>(11)`) for the initial manual tests.
  - Times `small1k` and `large100k` insertions with corresponding sizes $N / 0.75$.
  - Generates identical output formatting for clear comparative reading to show it mirrors native `HashMap` performance closely.

### PA7_Ex3.java (Polynomial Hash Code Collisions)
- **What it does**:
  - Reads `USDeclIndFormatted.txt` utilizing a whitespace-delimited `Scanner`.
  - Saves all unique words into a `HashSet` initially (skipping duplicates).
  - Uses a **Polynomial Hash Function** logic ($h \cdot a + \text{char}$) computed strictly in 32-bit `int` limits.
  - Tests parameter `a` = 1, 37, 40, 41. 
  - For each `a`, checks how many distinct words end up yielding identical 32-bit hash integers (hash collision vs bucket collision).
  - Prints the unique word count and the collision metrics for each `a`. 
  - We expect `a=1` to yield an incredibly high number of collisions, proving it's mathematically terrible.

## Verification Plan
After writing the files, I will proactively compile and execute them via the terminal to ensure they:
1. Don't throw any missing file exceptions (Data correctly found).
2. Produce rational output times that obey the algorithmic constraints detailed by your professor.
