package PA8;

import net.datastructures.AVLTreeMap;
import net.datastructures.Entry;
import net.datastructures.Position;
import java.io.File;
import java.util.Scanner;

/**
 * PA8 - Exercise 4: BetterPopMap (AVL-based Population DB)
 * Modified by: Aiden Wang
 */
class BetterPopMap {
    private AVLTreeMap<Integer, String> database = new AVLTreeMap<>();

    public BetterPopMap(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            if (!sc.hasNextInt()) return;
            int count = sc.nextInt();
            sc.nextLine();
            long totalExamined = 0;
            for (int i = 0; i < count && sc.hasNextLine(); i++) {
                String line = sc.nextLine();
                int firstComma = line.indexOf(',');
                if (firstComma != -1) {
                    int code = Integer.parseInt(line.substring(0, firstComma).trim());
                    String data = line.substring(firstComma + 1).trim();
                    
                    int before = database.nodesExamined;
                    database.put(code, data);
                    totalExamined += (database.nodesExamined - before);
                }
            }
            double avg = (double) totalExamined / count;
            System.out.println("AVL Tree built. Current Height: " + database.height(database.root()));
            System.out.printf("EC: Average nodes examined during AVL insertion: %.2f\n", avg);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void find(int code) {
        String val = database.get(code);
        System.out.println("Search " + code + ": " + (val != null ? val : "NOT found"));
    }

    public void insert(int code, String popCounty) {
        database.put(code, popCounty);
    }

    public void erase(int code) {
        database.remove(code);
    }

    public void print() {
        System.out.println("\n--- Population Database (AVL In-order) ---");
        inOrder(database.root());
        System.out.println("Total Tree Height: " + database.height(database.root()));
    }

    private void inOrder(Position<Entry<Integer, String>> p) {
        if (database.isExternal(p)) return;
        inOrder(database.left(p));
        System.out.println(p.getElement().getKey() + ": " + p.getElement().getValue() + " \t[Height: " + database.height(p) + "]");
        inOrder(database.right(p));
    }
}

public class PA8_Ex4 {
    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang");
        System.out.println("Modified by: Aiden Wang\n");

        BetterPopMap betterMap = new BetterPopMap("PAs/PA8/Data/popSmall.txt");

        System.out.println("\n1. Initial Records:");
        betterMap.print();

        System.out.print("\n2. "); betterMap.find(6037);
        System.out.print("3. "); betterMap.find(6000);
        
        betterMap.insert(6066, "1, New County, CA");
        betterMap.insert(6065, "2000, Riverside, CA");
        
        betterMap.erase(6999);
        betterMap.erase(6075);
        betterMap.erase(6055);

        System.out.println("\n9. Final Records after inserts/deletes:");
        betterMap.print();
    }
}
