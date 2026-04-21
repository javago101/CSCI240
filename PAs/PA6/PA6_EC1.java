package PA6;

import net.datastructures.Entry;
import net.datastructures.HeapPriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PA6_EC1 {

    // Comparator that compares strings in descending order
    public static class StringMaxComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a); // Reverse alphabetical/string comparison order
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");
        System.out.println("=== Running Extra Credit Option 1 ===");

        String inputFile = "PAs/PA6/data/large100k.txt";
        String outputFile = "PAs/PA6/data/large100k_EC_output.txt";

        sortStringsToOutputFile(inputFile, outputFile, 100000);
    }

    public static void sortStringsToOutputFile(String inputPath, String outputPath, int expectedSize) {
        List<Integer> data = new ArrayList<>(expectedSize);

        // 1. Read all integer data in first
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            while (scanner.hasNextInt()) {
                data.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find input file: " + inputPath);
            return;
        }

        System.out.println("Data loaded. Inserting into HeapPriorityQueue...");

        // 2. PQ uses <String, Integer> and a string descending comparator
        HeapPriorityQueue<String, Integer> maxStringPQ = new HeapPriorityQueue<>(new StringMaxComparator());

        // Insert <String, Integer> entries
        for (Integer val : data) {
            maxStringPQ.insert(String.valueOf(val), val);
        }

        System.out.println("Sorting and writing to output file...");

        // 3. Extract and write to file format exactly as requested: 5 values per line
        try (PrintWriter writer = new PrintWriter(new File(outputPath))) {
            int count = 0;

            while (!maxStringPQ.isEmpty()) {
                Entry<String, Integer> entry = maxStringPQ.removeMin();
                writer.print(entry.getKey());
                count++;

                // Format: 5 items per line space-separated
                if (count % 5 == 0 || maxStringPQ.isEmpty()) {
                    writer.println();
                } else {
                    writer.print(" ");
                }
            }
            System.out.println("\nSUCCESS: Output successfully written to -> " + outputPath);
            System.out.println("Please open this file and take your screenshot for submission!");

        } catch (FileNotFoundException e) {
            System.err.println("Failed to write to output file: " + e.getMessage());
        }
    }
}
