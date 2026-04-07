package PA5;

public class PA5_Ex2 {

    /*
    Algorithm BreadthFirst(T):
    if T is empty then return
    Initialize an empty Queue Q
    Q.enqueue(T.root())

    while Q is not empty do
        node = Q.dequeue()  { p is the oldest entry in the queue }
        visit(node) // e.g., print the node

        // Enqueue children in normal left-to-right order
        for each child in node.children() from left to right do
            Q.enqueue(child)  { add p's children to the end of the queue }


            Algorithm Trace:
Initialization: Start with an empty Queue Q. Enqueue the root node, A.
Q = [A]
Output = empty
Step 1: Dequeue A. Visit A. Enqueue its children from left to right (B, C, D).
Q = [B, C, D]
Output = A
Step 2: Dequeue B. Visit B. Enqueue its child (E).
Q = [C, D, E]
Output = A B
Step 3: Dequeue C. Visit C. It has no children, so enqueue nothing.
Q = [D, E]
Output = A B C
Step 4: Dequeue D. Visit D. Enqueue its children from left to right (F, G).
Q = [E, F, G]
Output = A B C D
Step 5: Dequeue E. Visit E. It has no children.
Q = [F, G]
Output = A B C D E
Step 6: Dequeue F. Visit F. It has no children.
Q = [G]
Output = A B C D E F
Step 7: Dequeue G. Visit G. It has no children.
Q = [] (Queue is now empty)
Output = A B C D E F G
Conclusion: The Queue is empty, meaning the algorithm successfully terminates. The final visited order is A B C D E F G, which perfectly matches the expected level-order output
.

     */
}
