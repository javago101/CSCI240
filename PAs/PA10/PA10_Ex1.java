package PA10;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * PA10 - Exercise 1: Randomized Quick Select
 * Author: Aiden Wang
 */
public class PA10_Ex1 {
    static long comparisons = 0;
    static long recursiveCalls = 0;
    static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang\n");

        int[] small = {4, 3, 1, 0, 9, 8, 6, 7, 10, 5, 2};
        int n = small.length; // 11
        
        System.out.println("=== Small List ===");
        runTest("Small List (k=1, smallest)", small.clone(), 1);
        runTest("Small List (k=n/2, median)", small.clone(), n / 2);
        runTest("Small List (k=n, largest)", small.clone(), n);

        System.out.println("\n=== Data Files ===");
        runFileTest("small1k.txt", 1000, 1000 / 2);
        runFileTest("large100k.txt", 100000, 100000 / 2);
    }

    static void runTest(String label, int[] arr, int k) {
        comparisons = 0;
        recursiveCalls = 0;
        int result = quickSelect(arr, 0, arr.length - 1, k);
        System.out.println(label + " -> kth smallest element: " + result + ", comparisons: " + comparisons + ", recursive calls: " + recursiveCalls);
    }

    static void runFileTest(String fileName, int size, int k) throws Exception {
        int[] data = new int[size];
        Scanner sc = new Scanner(new File("PAs/PA9/Data/" + fileName));
        for (int i = 0; i < size; i++) data[i] = sc.nextInt();
        sc.close();

        comparisons = 0;
        recursiveCalls = 0;
        long start = System.nanoTime();
        int result = quickSelect(data, 0, size - 1, k);
        long end = System.nanoTime();

        System.out.println(fileName + " (k=" + k + ", median) -> kth smallest element: " + result + ", comparisons: " + comparisons + ", recursive calls: " + recursiveCalls + ", time: " + (end - start) / 1000000.0 + " ms");
    }

    public static int quickSelect(int[] a, int left, int right, int k) {
        recursiveCalls++;
        if (left == right) {
            return a[left];
        }

        int pivotIndex = left + rand.nextInt(right - left + 1);
        pivotIndex = partition(a, left, right, pivotIndex);

        // The pivot is in its final sorted position
        int k_curr = pivotIndex - left + 1; // number of elements in left partition including pivot

        if (k == k_curr) {
            comparisons++;
            return a[pivotIndex];
        } else if (k < k_curr) {
            comparisons += 2;
            return quickSelect(a, left, pivotIndex - 1, k);
        } else {
            comparisons += 2;
            return quickSelect(a, pivotIndex + 1, right, k - k_curr);
        }
    }

    private static int partition(int[] a, int left, int right, int pivotIndex) {
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, right); // move pivot to end
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            comparisons++;
            if (a[i] < pivotValue) {
                swap(a, storeIndex, i);
                storeIndex++;
            }
        }
        swap(a, right, storeIndex); // Move pivot to its final place
        return storeIndex;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
