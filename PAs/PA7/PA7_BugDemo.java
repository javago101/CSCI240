package PA7;

import net.datastructures.ChainHashMap;
import net.datastructures.Entry;

/**
 * Demo of the Snapshot Iterator Bug in Textbook's HashMaps
 * This program demonstrates that deleting entries while iterating 
 * does not affect the iterator because it is a "stale" snapshot.
 */
public class PA7_BugDemo {
    public static void main(String[] args) {
        System.out.println("--- Textbook HashMap Bug Demo ---");
        
        ChainHashMap<Integer, String> map = new ChainHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        System.out.println("Initial Size: " + map.size());

        System.out.println("\nIterating and clearing the map inside the loop...");
        int count = 0;
        for (Entry<Integer, String> entry : map.entrySet()) {
            count++;
            System.out.println("Iteration " + count + ": Found Key " + entry.getKey());
            
            // BUG: We remove the key from the map, but the iterator keeps going!
            map.remove(entry.getKey());
            System.out.println("   > Map size after remove: " + map.size());
        }

        System.out.println("\nFinal Map Size: " + map.size());
        System.out.println("Total iterations performed: " + count);
        
        if (count > 0 && map.size() == 0) {
            System.out.println("\n[BUG CONFIRMED]: The iterator continued for " + count + 
                               " cycles even though the map was empty after the first removal!");
        }
    }
}
