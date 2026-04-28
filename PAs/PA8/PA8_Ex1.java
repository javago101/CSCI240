package PA8;

import net.datastructures.TreeMap;
import net.datastructures.Entry;
import net.datastructures.Position;

/**
 * PA8 - Exercise 1: BST (TreeMap) Test Driver
 * Modified by: Aiden Wang
 */
public class PA8_Ex1 {

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        TreeMap<Integer, String> bst = new TreeMap<>();

        // 1. Insert 10, 20, 4, 8, 15
        int[] keysToInsert = {10, 20, 4, 8, 15};
        System.out.println("Inserting keys:");
        for (int k : keysToInsert) {
            System.out.println("Inserted: " + k);
            bst.put(k, "Value-" + k);
        }

        // 2. Erase 8, 10
        System.out.println("\nErasing keys:");
        System.out.println("Erasing 8: " + bst.remove(8));
        System.out.println("Erasing 10: " + bst.remove(10));

        // 3. Search for 15, 30, 8
        System.out.println("\nSearching for keys:");
        int[] keysToSearch = {15, 30, 8};
        for (int k : keysToSearch) {
            String val = bst.get(k);
            if (val != null) {
                System.out.println("Found Key " + k + ": " + val);
            } else {
                System.out.println("Key " + k + " NOT Found.");
            }
        }

        // 4. Print the final BST (In-order)
        System.out.println("\nFinal BST Structure:");
        printTreeInOrder(bst, bst.root());
    }

    /**
     * Recursive helper to print the BST in-order.
     */
    private static void printTreeInOrder(TreeMap<Integer, String> map, Position<Entry<Integer, String>> p) {
        if (map.isExternal(p)) return;

        printTreeInOrder(map, map.left(p));
        System.out.println(p.getElement().getKey() + ": " + p.getElement().getValue());
        printTreeInOrder(map, map.right(p));
    }
}
