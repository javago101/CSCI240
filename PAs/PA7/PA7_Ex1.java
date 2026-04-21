package PA7;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PA7_Ex1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Modified by: Aiden Wang\n");

        // 1. Manual Operations Test
        HashMap<Integer, String> map = new HashMap<>();
        int[] keys = {13, 21, 5, 37, 15};
        
        System.out.print("Inserting: ");
        for (int k : keys) {
            System.out.print(k + " ");
            map.put(k, "Value-" + k);
        }
        System.out.println();

        System.out.println("Search 10: " + (map.containsKey(10) ? "Found" : "Missing"));
        System.out.println("Search 21: " + (map.containsKey(21) ? "Found" : "Missing"));

        map.remove(20);
        map.remove(37);
        System.out.println("After removing 37, Search 37: " + (map.containsKey(37) ? "Found" : "Missing"));

        System.out.println("\nEntries in map:");
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue());
        }

        // 2. Timing Benchmarks
        runTiming("PAs/PA7/Data/small1k.txt", 1000);
        runTiming("PAs/PA7/Data/large100k.txt", 100000);
    }

    private static void runTiming(String path, int n) throws Exception {
        HashMap<Integer, String> m = new HashMap<>((int)(n / 0.75));
        Scanner sc = new Scanner(new File(path));
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < n && sc.hasNextInt(); i++) {
            int k = sc.nextInt();
            m.put(k, "Data" + k);
        }
        sc.close();
        
        System.out.println("HashMap: Inserted " + n + " elements in " + (System.currentTimeMillis() - start) + "ms");
    }
}
