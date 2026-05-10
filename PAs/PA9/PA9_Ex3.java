package PA9;

import java.io.File;
import java.util.Scanner;

/**
 * PA9 - Exercise 3: Quick Sort
 * Author: Aiden Wang
 */
public class PA9_Ex3 {
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
        quickSort(small, 0, small.length - 1);
        
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
        quickSort(data, 0, size - 1);
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

    public static void quickSort(int[] a, int left, int right) {
        int size = right - left + 1;
        if (size <= 1) return;
        if (size == 2) {
            comparisons++;
            if (a[left] > a[right]) swap(a, left, right);
            return;
        }
        if (size == 3) {
            if (a[left] > a[left + 1]) swap(a, left, left + 1);
            if (a[left] > a[right]) swap(a, left, right);
            if (a[left + 1] > a[right]) swap(a, left + 1, right);
            comparisons += 3;
            return;
        }

        int pivot = medianOfThree(a, left, right);
        int part = partition(a, left, right, pivot);
        quickSort(a, left, part - 1);
        quickSort(a, part + 1, right);
    }

    public static int medianOfThree(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (a[left] > a[mid]) swap(a, left, mid);
        if (a[left] > a[right]) swap(a, left, right);
        if (a[mid] > a[right]) swap(a, mid, right);
        comparisons += 3;
        swap(a, mid, right - 1);
        return a[right - 1];
    }

    public static int partition(int[] a, int left, int right, int pivot) {
        int lp = left, rp = right - 1;
        while (true) {
            while (a[++lp] < pivot) comparisons++;
            while (a[--rp] > pivot) comparisons++;
            comparisons += 2;
            if (lp >= rp) break;
            swap(a, lp, rp);
        }
        swap(a, lp, right - 1);
        return lp;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        dataMoves += 3;
    }
    static void reset() { comparisons = 0; dataMoves = 0; }
    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}
