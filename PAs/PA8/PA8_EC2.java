package PA8;

import net.datastructures.AVLTreeMap;
import net.datastructures.Entry;
import net.datastructures.Position;
import java.io.File;
import java.util.Scanner;

/**
 * PA8 - Extra Credit Option 2
 * Visualizes the structure of the AVL Tree.
 * Modified by: Aiden Wang
 */
public class PA8_EC2 {

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");
        System.out.println("PA8 Extra Credit - AVL Tree Structure Drawing\n");

        AVLTreeMap<Integer, String> database = new AVLTreeMap<>();
        String filename = "PAs/PA8/Data/popSmall.txt";

        try (Scanner sc = new Scanner(new File(filename))) {
            if (sc.hasNextInt()) {
                int count = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < count && sc.hasNextLine(); i++) {
                    String line = sc.nextLine();
                    int comma = line.indexOf(',');
                    if (comma != -1) {
                        int code = Integer.parseInt(line.substring(0, comma).trim());
                        String data = line.substring(comma + 1).trim();
                        database.put(code, data);
                    }
                }
            }
            System.out.println("AVL Tree built. Current Height: " + database.height(database.root()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- AVL Tree Structure Drawing ---");
        drawRecurse(database, database.root(), 0);
    }

    private static void drawRecurse(AVLTreeMap<Integer, String> tree, Position<Entry<Integer, String>> p, int depth) {
        for (int i = 0; i < depth; i++) System.out.print("  ");

        if (tree.isExternal(p)) {
            System.out.println("[leaf]");
            return;
        }

        System.out.println(p.getElement().getKey());
        drawRecurse(tree, tree.left(p), depth + 1);
        drawRecurse(tree, tree.right(p), depth + 1);
    }
}
