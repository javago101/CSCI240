package PA9;

import java.io.File;
import java.util.Scanner;

/**
 * PA9 - Extra Credit: Heap Sort (In-place)
 * Author: Aiden Wang
 */
public class PA9_EC2 {
    static long comparisons = 0;
    static long dataMoves = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang (Extra Credit)\n");

        int size = 100000;
        int[] data = new int[size];
        Scanner sc = new Scanner(new File("PAs/PA9/Data/large100k.txt"));
        for (int i = 0; i < size; i++) data[i] = sc.nextInt();
        sc.close();

        System.out.println("=== large100k.txt Heap Sort Test ===");
        
        long start = System.nanoTime();
        heapSort(data);
        long end = System.nanoTime();

        System.out.print("First 5 values: ");
        for(int i=0; i<5; i++) System.out.print(data[i] + " ");
        System.out.println();
        System.out.print("Last 5 values:  ");
        for(int i=size-5; i<size; i++) System.out.print(data[i] + " ");
        System.out.println();
        
        System.out.println("Number of comparisons: " + comparisons);
        System.out.println("Number of data moves: " + dataMoves);
        System.out.println("Running time: " + (end - start) / 1000000.0 + " ms");
    }

    public static void heapSort(int[] a) {
        int n = a.length;
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            downHeap(a, n, i);
        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            downHeap(a, i, 0);
        }
    }

    static void downHeap(int[] a, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            comparisons++;
            if (a[left] > a[largest]) largest = left;
        }
        if (right < n) {
            comparisons++;
            if (a[right] > a[largest]) largest = right;
        }
        if (largest != i) {
            swap(a, i, largest);
            downHeap(a, n, largest);
        }
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        dataMoves += 3;
    }
}
