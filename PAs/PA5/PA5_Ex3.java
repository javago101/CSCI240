package PA5;

import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

public class PA5_Ex3 {

    /**
     * Custom tree class that extends the textbook's LinkedBinaryTree.
     * Overrides the positions() method to perform a pre-order traversal
     * instead of the default in-order traversal, as required by PA 5.
     */
    static class MyLinkedBinaryTree<E> extends LinkedBinaryTree<E> {
        @Override
        public Iterable<Position<E>> positions() {
            // Returns the pre-order iterable inherited from AbstractTree
            return preorder();
        }
    }

    public static void main(String[] args) {
        // Mandatory output requirement for PA5 submission
        System.out.println("Modified by: Aiden Wang\n");

        /* ==========================================
         * Test Case 1
         * ========================================== */
        MyLinkedBinaryTree<String> tree = new MyLinkedBinaryTree<>();

        // Constructing the binary tree exactly as requested:
        //        A
        //      /   \
        //     B     C
        //    / \   /
        //   D   E F

        Position<String> a = tree.addRoot("A");

        Position<String> b = tree.addLeft(a, "B");
        Position<String> c = tree.addRight(a, "C");

        Position<String> d = tree.addLeft(b, "D");
        Position<String> e = tree.addRight(b, "E");
        Position<String> f = tree.addLeft(c, "F");

        // Perform and print the Pre-order traversal
        System.out.println("Test Case 1:");
        System.out.print("Pre-order traversal for binary tree above: ");
        for (Position<String> p : tree.positions()) {
            System.out.print(p.getElement() + " ");
        }
        System.out.println("\n");

        /* ==========================================
         * Test Case 2
         * ========================================== */
        // Remove node C from the binary tree
        // Note: The textbook's remove() method replaces a removed node with its child (F)
        tree.remove(c);

        // Perform and print the Pre-order traversal on the updated tree
        System.out.println("Test Case 2:");
        System.out.print("Pre-order traversal for updated binary tree without node C: ");
        for (Position<String> p : tree.positions()) {
            System.out.print(p.getElement() + " ");
        }
        System.out.println();
    }
}