package PA5;

import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

public class PA5_EC2 {

    /**
     * Custom tree class that extends the textbook's LinkedBinaryTree.
     * Contains ONLY the modification required for Extra Credit 2.
     */
    static class MyLinkedBinaryTree<E> extends LinkedBinaryTree<E> {

        // --- PA5 EXTRA CREDIT OPTION 2 MODIFICATION ---
        // Prints the fully parenthesized arithmetic expression tree using an Euler tour.
        public void printExpression(Position<E> v) {
            if (v == null) return;

            // Pre-visit action: if the position is internal, print "("
            if (isInternal(v)) {
                System.out.print("(");
            }

            // Traverse left subtree
            if (left(v) != null) {
                printExpression(left(v));
            }

            // In-visit action: print the value or operator stored at the position
            System.out.print(v.getElement());

            // Traverse right subtree
            if (right(v) != null) {
                printExpression(right(v));
            }

            // Post-visit action: if the position is internal, print ")"
            if (isInternal(v)) {
                System.out.print(")");
            }
        }
    }

    public static void main(String[] args) {
        // Mandatory output requirement for PA5 submission [3, 4]
        System.out.println("Modified by: Aiden Wang\n");

        System.out.println("--- Extra Credit Option 2 ---");
        MyLinkedBinaryTree<String> mathTree = new MyLinkedBinaryTree<>();

        // Construct the expression tree: (7 * (3 + 6)) [2]
        //        *
        //      /   \
        //     7     +
        //          / \
        //         3   6

        Position<String> root = mathTree.addRoot("*");
        mathTree.addLeft(root, "7");

        Position<String> plusNode = mathTree.addRight(root, "+");
        mathTree.addLeft(plusNode, "3");
        mathTree.addRight(plusNode, "6");



        // Print the expression tree
        System.out.print("Expression Output (Expected (7*(3+6))): ");
        mathTree.printExpression(mathTree.root());
        System.out.println();
    }
}

