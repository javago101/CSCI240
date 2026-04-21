package PA7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * PA7 Extra Credit Option 2: Chain Hashing Performance Analysis.
 * Optimized for minimalist data output.
 */
public class PA7_EC2 {
    // Modified by: Aiden Wang

    private static class SimpleChainHashMap {
        private LinkedList<Integer>[] table;
        private int capacity;
        private long totalProbes = 0;
        private int maxProbes = 0;
        private static final int PRIME = 109345121;
        private long scale, shift;

        @SuppressWarnings("unchecked")
        public SimpleChainHashMap(int cap, long scale, long shift) {
            this.capacity = cap;
            this.table = new LinkedList[cap];
            this.scale = scale;
            this.shift = shift;
        }

        public void put(int key) {
            int hash = (int) ((Math.abs(key * scale + shift) % PRIME) % capacity);
            if (table[hash] == null) {
                table[hash] = new LinkedList<>();
            }
            int probes = 1 + table[hash].size();
            this.totalProbes += probes;
            if (probes > this.maxProbes) {
                this.maxProbes = probes;
            }
            table[hash].add(key);
        }

        public double getAverageProbes(int n) { return (double) totalProbes / n; }
        public int getMaxProbes() { return maxProbes; }
    }

    public static void main(String[] args) {
        String filePath = "PAs/PA7/Data/large100k.txt";
        int N = 100000;
        int[] data = new int[N];

        try (Scanner sc = new Scanner(new File(filePath))) {
            int i = 0;
            while (sc.hasNextInt() && i < N) {
                data[i++] = sc.nextInt();
            }
        } catch (FileNotFoundException e) {
            return;
        }

        Random rand = new Random(2024);
        long scale = rand.nextInt(109345121 - 1) + 1;
        long shift = rand.nextInt(109345121);

        double[] factors = {0.25, 0.5, 0.75, 0.9};

        for (double lf : factors) {
            int capacity = (int) Math.ceil(N / lf);
            SimpleChainHashMap map = new SimpleChainHashMap(capacity, scale, shift);

            for (int key : data) {
                map.put(key);
            }

            // Strictly required output fields
            System.out.println("Load Factor: " + lf);
            System.out.println("Table Size: " + capacity);
            System.out.printf("Average Probes: %.4f%n", map.getAverageProbes(N));
            System.out.println("Max Probes: " + map.getMaxProbes());
            System.out.println(); 
        }
    }
}
