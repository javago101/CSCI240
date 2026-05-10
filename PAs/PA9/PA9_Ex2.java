package PA9;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * PA9 - Exercise 2: Merge Sort
 * Author: Aiden Wang
 */
public class PA9_Ex2 {
    static long comparisons = 0;
    static long dataMoves = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang\n");

        // 1. Small List
        int[] small = {4, 3, 1, 0, 9, 8, 6, 7, 5, 2};
        System.out.println("=== Small List ===");
        System.out.print("Original List: ");
        printArray(small);
        
        reset();
        mergeSort(small, 0, small.length - 1);
        
        System.out.print("Sorted List:   ");
        printArray(small);
        System.out.println("Number of comparisons: " + comparisons);
        System.out.println("Number of data moves: " + dataMoves);

        // 2. Data Files
        runFileTest("small1k.txt", 1000);
        runFileTest("large100k.txt", 100000);
    }

    static void runFileTest(String fileName, int size) throws Exception {
        System.out.println("\n=== " + fileName + " ===");
        int[] data = new int[size];
        Scanner sc = new Scanner(new File("PAs/PA9/Data/" + fileName));
        for (int i = 0; i < size; i++) data[i] = sc.nextInt();
        sc.close();

        reset();
        long start = System.currentTimeMillis();
        mergeSort(data, 0, size - 1);
        long end = System.currentTimeMillis();

        System.out.print("First 5 values: ");
        for(int i=0; i<5; i++) System.out.print(data[i] + " ");
        System.out.println();
        System.out.print("Last 5 values:  ");
        for(int i=size-5; i<size; i++) System.out.print(data[i] + " ");
        System.out.println();
        
        System.out.println("Number of comparisons: " + comparisons);
        System.out.println("Number of data moves: " + dataMoves);
        System.out.println("Running time: " + (end - start) + " ms");
    }

    public static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    public static void merge(int[] a, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(a, left, mid + 1);
        int[] R = Arrays.copyOfRange(a, mid + 1, right + 1);
        dataMoves += (L.length + R.length);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            comparisons++;
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
            dataMoves++;
        }
        while (i < L.length) { a[k++] = L[i++]; dataMoves++; }
        while (j < R.length) { a[k++] = R[j++]; dataMoves++; }
    }

    static void reset() { comparisons = 0; dataMoves = 0; }
    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}
