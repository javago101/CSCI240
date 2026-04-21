package PA7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class PA7_EC2 {
    // Modified by: Aiden Wang

    /**
     * A standalone, simplified Chain Hash Map implementation used to track "Probes" 
     * across different load factors without internal library resizing.
     */
    private static class SimpleChainHashMap {
        private LinkedList<Integer>[] table;
        private int capacity;
        private long totalProbes = 0;
        private int maxProbes = 0;

        // MAD compression parameters (Multiply-Add-Divide)
        private static final int PRIME = 109345121;
        private long scale, shift;

        @SuppressWarnings("unchecked")
        public SimpleChainHashMap(int cap) {
            this.capacity = cap;
            this.table = new LinkedList[cap]; // Array of buckets
            
            Random rand = new Random();
            this.scale = rand.nextInt(PRIME - 1) + 1;
            this.shift = rand.nextInt(PRIME);
        }

        /**
         * Simulates a 'put' operation and tracks the number of probes required.
         * A probe is defined as 1 (initial bucket access) + the number of existing 
         * elements in that bucket's list.
         */
        public void put(int key) {
            // Apply MAD compression: [(ay + b) % p] % N
            int hash = (int) ((Math.abs(key * scale + shift) % PRIME) % capacity);
            
            if (table[hash] == null) {
                table[hash] = new LinkedList<>();
            }

            // --- PROBE CALCULATION ---
            // 1 probe to access the bucket location
            // + 1 probe for every element currently in the list (search time)
            int currentBucketProbes = 1 + table[hash].size();
            
            this.totalProbes += currentBucketProbes;
            if (currentBucketProbes > this.maxProbes) {
                this.maxProbes = currentBucketProbes;
            }

            // For the purpose of tracking performance, we add the key to the bucket
            table[hash].add(key);
        }

        public double getAverageProbes(int n) {
            return (double) totalProbes / n;
        }

        public int getMaxProbes() {
            return maxProbes;
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");
        System.out.println("=== PA7 Extra Credit Option 2: Chain Hashing Probing Analysis ===");

        String filePath = "PAs/PA7/Data/large100k.txt";
        int totalElements = 100000;
        int[] data = new int[totalElements];

        // 1. Read the input file into an array first
        try (Scanner sc = new Scanner(new File(filePath))) {
            int i = 0;
            while (sc.hasNextInt() && i < totalElements) {
                data[i++] = sc.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found! Please check: " + filePath);
            return;
        }

        // 2. Test fixed Load Factors: 0.25, 0.5, 0.75, 0.9
        double[] loadFactors = {0.25, 0.5, 0.75, 0.9};

        for (double lf : loadFactors) {
            // Calculate capacity such that N / Capacity = Load Factor
            int capacity = (int) Math.ceil(totalElements / lf);
            
            SimpleChainHashMap map = new SimpleChainHashMap(capacity);

            // Insert all data
            for (int key : data) {
                map.put(key);
            }

            // Results output
            System.out.println("\n--- Results for Load Factor: " + lf + " ---");
            System.out.println("Total Items (N)     : " + totalElements);
            System.out.println("Array Capacity (C)  : " + capacity);
            System.out.printf("Average Probes      : %.4f%n", map.getAverageProbes(totalElements));
            System.out.println("Max Probes in Bucket: " + map.getMaxProbes());
        }
        
        System.out.println("\nAnalysis: As the Load Factor approaches 1.0, the average number of probes ");
        System.out.println("increases as buckets become more likely to contain multiple entries.");
    }
}
