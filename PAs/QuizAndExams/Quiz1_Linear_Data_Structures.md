# CSCI240 Mock Exam: Quiz 1
**Topic Module**: Java Primer, Arrays, Linked Lists, Stacks & Queues (PA1 - PA4)

---

### Section 1: Multiple Choice
*Hint: Choose the absolute best Big-O behavior or concept fitting the scenario.*

**1. A textbook states that an algorithm executes an exact number of primitive operations defined by the formula $f(n) = 3n^2 + 50n + \log(n)$. What is the exact Big-O Time Complexity?**
a) $O(n^3)$
b) $O(\log n)$
c) $O(n \log n)$
d) $O(n^2)$

**2. You are implementing a memory log where items must be retrieved strictly in a Last-In, First-Out (LIFO) order. Which Abstract Data Type optimally supports this logic in $O(1)$ time?**
a) Circular Queue
b) Max Priority Queue
c) Stack
d) Positional List

**3. In a Singly Linked List lacking a `Tail` pointer, what is the Time Complexity for inserting a new node exactly at the END of the list?**
a) $O(1)$
b) $O(\log n)$
c) $O(n)$
d) $O(n^2)$

**4. You have implemented a Queue utilizing a statically sized basic Array of size $N$. Which formula acts as the "wrap-around" physics mechanism to prevent an `ArrayIndexOutOfBoundsException` when the Tail reaches index $N-1$?**
a) `index = index % (N - 1)`
b) `index = (index + 1) / N`
c) `index = (index + 1) % N`
d) `index = N % (index + 1)`

---

### Section 2: Short Answer & Code Logic
**5. Memory Leak Danger:** In PA2, you built a Singly Linked List using a custom `Node<E>` class. Briefly explain why losing the `Head` reference instantly destroys the capability of accessing the rest of the list. What component of Java physically cleans up the unreachable memory behind the scenes?

**6. Positional Abstraction:** In PA3, you developed a Text Editor utilizing an Abstract `Position` interface rather than standard array index integers `(0, 1, 2...)`. Explain exactly why using `Position<E>` makes arbitrary mid-list insertions fundamentally faster ($O(1)$) compared to a generic Java `ArrayList.add(index)`.
