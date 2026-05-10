package PA9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * PA9 - Extra Credit Option 2: Heap Sort (In-place)
 * Modified by: Aiden Wang
 */
public class PA9_EC2 {

    static long comparisons = 0;
    static long dataMoves = 0;

    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        // --- SMALL LIST TEST ---
        int[] smallList = {4, 3, 1, 0, 9, 8, 6, 7, 5, 2};
        runExperiment("Original Small List", smallList.clone());

        // --- DATA FILE TESTS ---
        testFile("small1k.txt", 1000);
        testFile("large100k.txt", 100000);
    }

    public static void testFile(String filename, int size) {
        System.out.println("\n--- Testing with " + filename + " ---");
        int[] data = loadData("PAs/PA9/Data/" + filename, size);
        if (data != null) {
            resetMetrics();
            long startTime = System.currentTimeMillis();
            heapSort(data);
            long endTime = System.currentTimeMillis();

            System.out.print("First 5: ");
            for (int i = 0; i < 5; i++) System.out.print(data[i] + " ");
            System.out.println();
            System.out.print("Last 5:  ");
            for (int i = data.length - 5; i < data.length; i++) System.out.print(data[i] + " ");
            System.out.println();

            System.out.println("Comparisons: " + comparisons);
            System.out.println("Data Moves:  " + dataMoves);
            System.out.println("Running Time: " + (endTime - startTime) + " ms");
        }
    }

    public static void runExperiment(String label, int[] array) {
        System.out.println("=== " + label + " ===");
        System.out.print("Original: ");
        printArray(array);

        resetMetrics();
        heapSort(array);

        System.out.print("Sorted:   ");
        printArray(array);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Data Moves:  " + dataMoves);
        System.out.println();
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        // Phase 1: Build max heap (Bottom-up)
        for (int i = n / 2 - 1; i >= 0; i--) {
            downHeap(array, n, i);
        }

        // Phase 2: Extract elements
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            downHeap(array, i, 0);
        }
    }

    public static void downHeap(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            comparisons++;
            if (array[left] > array[largest]) {
                largest = left;
            }
        }

        if (right < n) {
            comparisons++;
            if (array[right] > array[largest]) {
                largest = right;
            }
        }

        if (largest != i) {
            swap(array, i, largest);
            downHeap(array, n, largest);
        }
    }

    public static void swap(int[] array, int idx1, int idx2) {
        int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
        dataMoves += 3;
    }

    private static void resetMetrics() {
        comparisons = 0;
        dataMoves = 0;
    }

    private static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    private static int[] loadData(String path, int size) {
        try (Scanner sc = new Scanner(new File(path))) {
            int[] data = new int[size];
            int i = 0;
            while (sc.hasNextInt() && i < size) {
                data[i++] = sc.nextInt();
            }
            return data;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
            return null;
        }
    }
}
