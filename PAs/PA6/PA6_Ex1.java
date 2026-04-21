package PA6;

import net.datastructures.Entry;
import net.datastructures.SortedPriorityQueue;
import java.util.Comparator;

public class PA6_Ex1 {

    public static class MinComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    public static class MaxComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b.compareTo(a); // Reverse comparison order
        }
    }

    public static void main(String[] args) {

        System.out.println("Modified by: Aiden Wang\n");

        SortedPriorityQueue<Integer, String> minPQ = new SortedPriorityQueue<>(new MinComparator());
        SortedPriorityQueue<Integer, String> maxPQ = new SortedPriorityQueue<>(new MaxComparator());

        System.out.println("=== Testing Min-Priority Queue (Lowest value has highest priority) ===");
        testPQ(minPQ);

        System.out.println("\n=== Testing Max-Priority Queue (Largest value has highest priority) ===");
        testPQ(maxPQ);
    }

    /**
     * Encapsulates a series of test operations required in the assignment
     */
    public static void testPQ(SortedPriorityQueue<Integer, String> pq) {
        // insert(5, “five”), insert(4, “four”), insert(7, “seven”), insert(1, “one”)
        pq.insert(5, "five");
        pq.insert(4, "four");
        pq.insert(7, "seven");
        pq.insert(1, "one");

        // min(), removeMin()
        printMin(pq);
        pq.removeMin();

        // insert(3, “three”), insert(6, “six”)
        pq.insert(3, "three");
        pq.insert(6, "six");

        // min(), removeMin(), min(), removeMin()
        printMin(pq);
        pq.removeMin();
        printMin(pq);
        pq.removeMin();

        // insert(8, “eight”)
        pq.insert(8, "eight");

        // min(), removeMin()
        printMin(pq);
        pq.removeMin();

        // insert(2, “two”)
        pq.insert(2, "two");

        // min(), removeMin(), min(), removeMin()
        printMin(pq);
        pq.removeMin();
        printMin(pq);
        pq.removeMin();
    }

    /**
     * Helper method: performs min() operation and prints the int and string
     * key-value pair
     */
    private static void printMin(SortedPriorityQueue<Integer, String> pq) {
        Entry<Integer, String> minEntry = pq.min();
        if (minEntry != null) {
            System.out
                    .println("min() output -> Key: " + minEntry.getKey() + ", Value: \"" + minEntry.getValue() + "\"");
        }
    }
}
