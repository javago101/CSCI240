package PAs.PA7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import net.datastructures.ChainHashMap;
import net.datastructures.Entry;

public class PA7_Ex2 {
    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        System.out.println("=== Part A: Manual ChainHashMap Operations ===");
        runManualTests();

        System.out.println("\n=== Part B: Inserting small1k.txt ===");
        timeInsertion("PAs/PA7/Data/small1k.txt", 1000);

        System.out.println("\n=== Part C: Inserting large100k.txt ===");
        timeInsertion("PAs/PA7/Data/large100k.txt", 100000);
        System.out.println("Notice how they are very similar to the times in exercise 1!");
    }

    private static void runManualTests() {
        // Use capacity 11 as requested
        ChainHashMap<Integer, String> map = new ChainHashMap<>(11);

        // Insert initial entries
        int[] keys = {13, 21, 5, 37, 15};
        System.out.print("Inserting keys: ");
        for (int key : keys) {
            System.out.print(key + " ");
            String value = new StringBuilder(String.valueOf(key)).reverse().toString();
            map.put(key, value);
        }
        System.out.println();

        // Search for 10 and 21
        searchAndPrint(map, 10);
        searchAndPrint(map, 21);

        // Remove 20, 37
        System.out.println("\n> Removing 20 and 37...");
        map.remove(20);
        map.remove(37);

        // Search for 37
        searchAndPrint(map, 37);

        // Use iterator to print
        System.out.println("\n> Map Entries via Iterator:");
        for (Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " -> Value: " + entry.getValue());
        }
    }

    private static void searchAndPrint(ChainHashMap<Integer, String> map, int key) {
        // By definition in net.datastructures.Map, get() returns null if the key is not found
        if (map.get(key) != null) {
            System.out.println("Searched for " + key + " -> Found! Value: " + map.get(key));
        } else {
            System.out.println("Searched for " + key + " -> Not Found.");
        }
    }

    private static void timeInsertion(String filePath, int count) {
        int capacity = (int) Math.ceil(count / 0.75);
        ChainHashMap<Integer, String> map = new ChainHashMap<>(capacity);
        int[] data = new int[count];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            int i = 0;
            while (scanner.hasNextInt() && i < count) {
                data[i++] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + filePath);
            System.out.println("Make sure you are running from the project root.");
            return;
        }

        long startTime = System.currentTimeMillis();
        for (int key : data) {
            String value = new StringBuilder(String.valueOf(key)).reverse().toString();
            map.put(key, value);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Successfully inserted " + count + " elements into ChainHashMap.");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
