package PA8;

import net.datastructures.AVLTreeMap;
import net.datastructures.Entry;
import net.datastructures.Position;

/**
 * PA8 - Exercise 3: AVL (AVLTreeMap) Test Driver
 * Modified by: Aiden Wang
 */
public class PA8_Ex3 {

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();

        // 1. Insert 10, 20, 4, 8, 15
        int[] keysToInsert = {10, 20, 4, 8, 15};
        System.out.println("--- Inserting Keys into AVL ---");
        for (int k : keysToInsert) {
            System.out.println("Inserting Key: " + k);
            avl.put(k, "Name-" + k);
        }

        // 2. Erase 8, 10
        System.out.println("\n--- Erasing Keys ---");
        System.out.println("Erasing 8: " + avl.remove(8));
        System.out.println("Erasing 10: " + avl.remove(10));

        // 3. Search for 15, 30, 8
        System.out.println("\n--- Searching for Keys ---");
        int[] keysToSearch = {15, 30, 8};
        for (int k : keysToSearch) {
            String val = avl.get(k);
            System.out.println("Key " + k + ": " + (val != null ? "Found (" + val + ")" : "NOT Found"));
        }

        // 4. Print the final AVL Tree with Heights
        System.out.println("\n--- Final AVL Structure (In-order, showing heights) ---");
        printAVLInOrder(avl, avl.root());
    }

    private static void printAVLInOrder(AVLTreeMap<Integer, String> tree, Position<Entry<Integer, String>> p) {
        if (tree.isExternal(p)) return;

        printAVLInOrder(tree, tree.left(p));
        System.out.println("Key: " + p.getElement().getKey() + "\tVal: " + p.getElement().getValue() + "\tHeight: " + tree.height(p));
        printAVLInOrder(tree, tree.right(p));
    }
}
