package PA2;

// Author: Aiden Wang
import java.util.Random;

public class PA2_EC2 {

    // O(n^3) Algorithm using a triple nested loop
    public static int maxSubArraySum(int[] x) {
        int n = x.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = 0;
                for (int k = i; k <= j; k++) {
                    currentSum += x[k];
                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang\n");

        int[] test1 = {31, -41, 59, 26, -53, 58, 97, -93, -23, 84};
        System.out.println("Test 1 Max Sum (Expected 187): " + maxSubArraySum(test1));

        int[] test2 = {31, 41, 59, 26, 53, 58, 97, 93, 23, 84};
        System.out.println("Test 2 Max Sum (Expected 565): " + maxSubArraySum(test2));

        // Timing Experiments
        int[] sizes = {100, 1000, 10000};
        Random rand = new Random();

        System.out.println("\nRunning Time Experiments:");
        System.out.printf("%-10s | %-15s\n", "n", "Time (ms)");
        System.out.println("------------------------------");

        for (int n : sizes) {
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = -n + rand.nextInt(2 * n + 1);
            }

            long startTime = System.currentTimeMillis();
            maxSubArraySum(x);
            long timeMs = System.currentTimeMillis() - startTime;

            System.out.printf("%-10d | %-15d\n", n, timeMs);
        }
    }
}
