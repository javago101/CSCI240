package PA8;

import net.datastructures.TreeMap;
import net.datastructures.Entry;
import net.datastructures.Position;
import java.io.File;
import java.util.Scanner;

/**
 * PA8 - Exercise 2: SimplePopMap (BST-based Population DB)
 * Modified by: Aiden Wang
 */
class SimplePopMap {
    private TreeMap<Integer, String> database = new TreeMap<>();

    public SimplePopMap(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            if (!sc.hasNextInt()) return;
            int count = sc.nextInt();
            sc.nextLine(); // consume newline

            long totalInsertOps = 0;
            for (int i = 0; i < count && sc.hasNextLine(); i++) {
                String line = sc.nextLine();
                int firstComma = line.indexOf(',');
                if (firstComma != -1) {
                    int code = Integer.parseInt(line.substring(0, firstComma).trim());
                    String data = line.substring(firstComma + 1).trim();
                    
                    int before = database.nodesExamined;
                    database.put(code, data);
                    totalInsertOps += (database.nodesExamined - before);
                }
            }
            double avg = (double) totalInsertOps / count;
            System.out.println("Tree built from " + filename + ". Initial Height: " + database.height(database.root()));
            System.out.printf("EC: Average nodes examined during insertion: %.2f\n", avg);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void find(int code) {
        String val = database.get(code);
        if (val != null) {
            System.out.println("Found County " + code + ": " + val);
        } else {
            System.out.println("County " + code + " NOT found.");
        }
    }

    public void insert(int code, String popCounty) {
        if (database.get(code) != null) {
            System.out.println("Replacing County " + code);
        } else {
            System.out.println("Inserting NEW County " + code);
        }
        database.put(code, popCounty);
    }

    public void erase(int code) {
        String removed = database.remove(code);
        if (removed != null) {
            System.out.println("Deleted County " + code);
        } else {
            System.out.println("County " + code + " not found for deletion.");
        }
    }

    public void print() {
        System.out.println("\n--- Current Records (In-order) ---");
        inOrderPrint(database.root());
        System.out.println("Tree Total Height: " + database.height(database.root()));
    }

    private void inOrderPrint(Position<Entry<Integer, String>> p) {
        if (database.isExternal(p)) return;
        inOrderPrint(database.left(p));
        System.out.println(p.getElement().getKey() + ": " + p.getElement().getValue());
        inOrderPrint(database.right(p));
    }
}

public class PA8_Ex2 {
    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang");
        System.out.println("Modified by: Aiden Wang\n");

        SimplePopMap popMap = new SimplePopMap("PAs/PA8/Data/popSmall.txt");

        // Test Cases from instructions
        System.out.println("\n--- Running Test Cases ---");
        System.out.println("1. Listing all records:");
        popMap.print();

        System.out.print("\n2. "); popMap.find(6037);
        System.out.print("3. "); popMap.find(6000);
        
        System.out.print("4. "); popMap.insert(6066, "1, New County, CA");
        System.out.print("5. "); popMap.insert(6065, "2000, Riverside, CA");
        
        System.out.print("6. "); popMap.erase(6999);
        System.out.print("7. "); popMap.erase(6075);
        System.out.print("8. "); popMap.erase(6055);

        System.out.println("\n9. Listing records after modifications:");
        popMap.print();
    }
}
