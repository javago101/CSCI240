/* Java Class:
Author:Aiden Wang
Class: CSCI 240
Date:
Description:
I certify that the code below is my own work.
Exception(s): N/A
*/
package PA2;

import java.util.Random;

public class EX1 {

    // O(n^2) Algorithm
    public static double[] prefixAverage1(double[] x) {
        int n = x.length;
        double[] a = new double[n];
        for (int j = 0; j < n; j++) {
            double total = 0;
            for (int i = 0; i <= j; i++) {
                total += x[i];
            }
            a[j] = total / (j + 1);
        }
        return a;
    }

    // O(n) Algorithm
    public static double[] prefixAverage2(double[] x) {
        int n = x.length;
        double[] a = new double[n];
        double total = 0;
        for (int j = 0; j < n; j++) {
            total += x[j];
            a[j] = total / (j + 1);
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang\n");

        // 1. Test case to confirm correctness
        double[] testArr = {31, -41, 59, 26, -53, 58, 97, -93, -23, 84};
        double[] result = prefixAverage2(testArr);
        System.out.print("Prefix averages of test case: ");
        for (double val : result) {
            System.out.printf("%.2f ", val);
        }
        System.out.println("\n");

        // 2. Running Time Experiments
        int[] sizes = {100, 1000, 10000, 100000};
        Random rand = new Random();

        System.out.printf("%-10s | %-20s | %-20s\n", "n", "prefixAverage1 (ns)", "prefixAverage2 (ns)");
        System.out.println("---------------------------------------------------------------");

        for (int n : sizes) {
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = -n + (rand.nextDouble() * (2 * n)); // Random between -n and n
            }

            long startTime1 = System.nanoTime();
            prefixAverage1(x);
            long endTime1 = System.nanoTime();
            long time1 = endTime1 - startTime1;

            long startTime2 = System.nanoTime();
            prefixAverage2(x);
            long endTime2 = System.nanoTime();
            long time2 = endTime2 - startTime2;

            System.out.printf("%-10d | %-20d | %-20d\n", n, time1, time2);
        }

        System.out.println("\nConclusion: The times collected match the Big-O notation. prefixAverage1 grows quadratically (O(n^2)), while prefixAverage2 grows linearly (O(n)).");
    }
}
