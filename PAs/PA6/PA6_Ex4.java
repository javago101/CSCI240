package PA6;

import net.datastructures.Entry;
import net.datastructures.HeapPriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PA6_Ex4 {

    public static class MaxComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b.compareTo(a); // Reverse comparison order for Max-Priority Queue
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        System.out.println("=== Sorting small1k.txt using HeapPriorityQueue ===");
        sortAndMeasure("PAs/PA6/data/small1k.txt", 1000);

        System.out.println("\n=== Sorting large100k.txt using HeapPriorityQueue ===");
        System.out.println("Notice how much faster this O(n log n) algorithm runs compared to Ex2!");
        sortAndMeasure("PAs/PA6/data/large100k.txt", 100000);
    }

    public static void sortAndMeasure(String filename, int expectedSize) {
        List<Integer> data = new ArrayList<>(expectedSize);

        // Read all data into memory first to avoid counting Disk I/O towards sorting
        // runtime
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextInt()) {
                data.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file: " + filename);
            System.err.println("Make sure to run the program from the project root.");
            return;
        }

        HeapPriorityQueue<Integer, String> maxPQ = new HeapPriorityQueue<>(new MaxComparator());
        List<Integer> sortedData = new ArrayList<>(data.size());

        // Start timing
        long startTime = System.currentTimeMillis();

        // Phase 1: Insert all elements into max PQ
        for (Integer val : data) {
            maxPQ.insert(val, String.valueOf(val));
        }

        // Phase 2: Extract all elements (they will come out in descending order)
        while (!maxPQ.isEmpty()) {
            Entry<Integer, String> entry = maxPQ.removeMin();
            sortedData.add(entry.getKey());
        }

        // End timing
        long endTime = System.currentTimeMillis();
        long elapsedMs = endTime - startTime;

        // Output formatting: first 5 and last 5 elements
        System.out.print("First 5 values: ");
        for (int i = 0; i < Math.min(5, sortedData.size()); i++) {
            System.out.print(sortedData.get(i) + " ");
        }
        System.out.println();

        System.out.print("Last 5 values:  ");
        for (int i = Math.max(0, sortedData.size() - 5); i < sortedData.size(); i++) {
            System.out.print(sortedData.get(i) + " ");
        }
        System.out.println();

        System.out.println("-> Total sorting time: " + elapsedMs + " ms");
    }
}
