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

        int[] small = {4, 3, 1, 0, 9, 8, 6, 7, 5, 2};
        reset();
        quickSort(small, 0, small.length - 1);
        System.out.print("Sorted Small: ");
        for(int x : small) System.out.print(x + " ");
        System.out.println("\nComparisons: " + comparisons);

        // large100k
        int[] large = new int[100000];
        Scanner sc = new Scanner(new File("PAs/PA9/Data/large100k.txt"));
        for (int i = 0; i < 100000; i++) large[i] = sc.nextInt();
        sc.close();

        reset();
        long start = System.currentTimeMillis();
        quickSort(large, 0, 99999);
        long end = System.currentTimeMillis();
        System.out.println("large100k -> Time: " + (end - start) + "ms, Comparisons: " + comparisons);
    }

    public static void quickSort(int[] a, int left, int right) {
        if (right - left <= 0) return;
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
}
