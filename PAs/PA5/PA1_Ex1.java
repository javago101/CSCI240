package PA5;

public class PA1_Ex1 {

    /*

    Pseudocode:
Algorithm IterativePreorder(T):
    if T is empty then return
    Initialize an empty Stack S
    S.push(T.root())

    while S is not empty do
        node = S.pop()
        visit(node) // e.g., print the node

        // Push children to stack in reverse order
        for each child in node.children() from right to left do
            S.push(child)


Trace for the provided tree:
Tree Structure: Root A; A has children (B, C, D); B has child (E); D has children (F, G)
.
Step 1: S = [A]
Step 2: Pop A (Visit A). Push children D, C, B. S = [D, C, B]
Step 3: Pop B (Visit B). Push child E. S = [D, C, E]
Step 4: Pop E (Visit E). No children. S = [D, C]
Step 5: Pop C (Visit C). No children. S = [D]
Step 6: Pop D (Visit D). Push children G, F. S = [G, F]
Step 7: Pop F (Visit F). No children. S = [G]
Step 8: Pop G (Visit G). No children. S = []
Final Output: A B E C D F G (Matches the required output!)


    */
}
