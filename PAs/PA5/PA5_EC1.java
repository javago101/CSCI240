package PA5;

public class PA5_EC1 {
    /*
    To achieve post-order iteratively, a clever trick is to use two stacks. Stack 1 does a modified preorder traversal, and Stack 2 reverses that order to yield a perfect post-order
.
Pseudocode:
Algorithm IterativePostorder(T):
    if T is empty then return
    Initialize Stack S1 and Stack S2
    S1.push(T.root())

    while S1 is not empty do
        node = S1.pop()
        S2.push(node)

        // Push children to S1 in normal left-to-right order
        for each child in node.children() from left to right do
            S1.push(child)

    // S2 now contains the post-order sequence
    while S2 is not empty do
        node = S2.pop()
        visit(node)
Trace for the tree:
Step 1: S1 = [A], S2 = []
Step 2: Pop A to S2. Push B, C, D to S1. S1 = [B, C, D], S2 = [A]
Step 3: Pop D to S2. Push F, G to S1. S1 = [B, C, F, G], S2 = [A, D]
Step 4: Pop G to S2. S1 = [B, C, F], S2 = [A, D, G]
Step 5: Pop F to S2. S1 = [B, C], S2 = [A, D, G, F]
Step 6: Pop C to S2. S1 = [B], S2 = [A, D, G, F, C]
Step 7: Pop B to S2. Push E to S1. S1 = [E], S2 = [A, D, G, F, C, B]
Step 8: Pop E to S2. S1 = [], S2 = [A, D, G, F, C, B, E]
Step 9: Pop everything from S2 to print: E B C F G D A (Matches the required output!)

     */
}
