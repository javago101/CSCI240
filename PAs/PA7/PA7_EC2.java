package PA7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import net.datastructures.AbstractHashMap;
import net.datastructures.Entry;
import net.datastructures.UnsortedTableMap;

public class PA7_EC2 {

    // Custom map embedded directly to natively track Professor's exact definition of Probes
    public static class InstrumentedChainHashMap<K, V> extends AbstractHashMap<K, V> {
        private UnsortedTableMap<K, V>[] table;

        public long totalProbes = 0;
        public int maxProbes = 0;

        public InstrumentedChainHashMap(int cap) {
            super(cap);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void createTable() {
            table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
        }

        @Override
        protected V bucketGet(int h, K k) {
            UnsortedTableMap<K, V> bucket = table[h];
            if (bucket == null) return null;
            return bucket.get(k);
        }

        @Override
        protected V bucketPut(int h, K k, V v) {
            UnsortedTableMap<K, V> bucket = table[h];

            // A null bucket implies it's empty, so size is essentially 0 initially
            int priorSize = (bucket == null ? 0 : bucket.size());

            // Professor rule: 1 probe to check initial location + 1 probe for every item evaluated inside the bucket chaining
            int probesForInsertion = priorSize + 1;

            totalProbes += probesForInsertion;
            maxProbes = Math.max(maxProbes, probesForInsertion);

            if (bucket == null) {
                bucket = table[h] = new UnsortedTableMap<>();
            }

            int oldSize = bucket.size();
            V answer = bucket.put(k, v);
            n += (bucket.size() - oldSize);
            return answer;
        }

        @Override
        protected V bucketRemove(int h, K k) {
            UnsortedTableMap<K, V> bucket = table[h];
            if (bucket == null) return null;
            int oldSize = bucket.size();
            V answer = bucket.remove(k);
            n -= (oldSize - bucket.size());
            return answer;
        }

        @Override
        public Iterable<Entry<K, V>> entrySet() {
            ArrayList<Entry<K, V>> buffer = new ArrayList<>();
            for (int h = 0; h < capacity; h++)
                if (table[h] != null)
                    for (Entry<K, V> entry : table[h].entrySet())
                        buffer.add(entry);
            return buffer;
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");
        System.out.println("=== PA7 Extra Credit Option 2: Chain Hashing Probing Tests ===");

        String filePath = "PAs/PA7/Data/large100k.txt";
        int count = 100000;
        int[] data = new int[count];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            int i = 0;
            while (scanner.hasNextInt() && i < count) {
                data[i++] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + filePath);
            System.out.println("Ensure you run this from the project root.");
            return;
        }

        double[] loadFactors = {0.25, 0.5, 0.75, 0.9};

        for (double lf : loadFactors) {
            int capacity = (int) Math.ceil(count / lf);
            InstrumentedChainHashMap<Integer, String> map = new InstrumentedChainHashMap<>(capacity);

            // Populate Map
            for (int key : data) {
                String value = new StringBuilder(String.valueOf(key)).reverse().toString();
                map.put(key, value);
            }

            // Calculation and formatted print
            double averageProbes = (double) map.totalProbes / count;

            System.out.println("\nLoad Factor: " + lf);
            System.out.println("-------------------------");
            System.out.println("Table Size (N/LF)   : " + capacity);
            System.out.printf("Average Probes      : %.4f%n", averageProbes);
            System.out.println("Maximum Worst Probes: " + map.maxProbes);
        }
    }
}
