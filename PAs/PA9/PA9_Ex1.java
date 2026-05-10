package PA9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * PA9 - Exercise 1: Insertion Sort & Inversions
 * Author: Aiden Wang
 */
public class PA9_Ex1 {

    static long comparisons = 0;
    static long dataMoves = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang\n");

        // 1. Small List Test
        int[] small = {4, 3, 1, 0, 9, 8, 6, 7, 5, 2};
        System.out.println("=== Original Small List ===");
        System.out.print("Original: ");
        printArray(small);
        
        int inv = countInversions(small, small.length);
        insertionSort(small, small.length);
        
        System.out.print("Sorted:   ");
        printArray(small);
        System.out.println("Inversions: " + inv);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Data Moves: " + dataMoves);
        System.out.println();

        // 2. small1k.txt Test
        int[] data = new int[1000];
        try {
            Scanner sc = new Scanner(new File("PAs/PA9/Data/small1k.txt"));
            for (int i = 0; i < 1000; i++) data[i] = sc.nextInt();
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find small1k.txt");
            return;
        }

        System.out.println("=== small1k.txt Random ===");
        long invStart = System.currentTimeMillis();
        int inv1k = countInversions(data, 1000);
        long invEnd = System.currentTimeMillis();
        
        long sortStart = System.currentTimeMillis();
        insertionSort(data, 1000);
        long sortEnd = System.currentTimeMillis();

        System.out.print("First 5: ");
        for(int i=0; i<5; i++) System.out.print(data[i] + " ");
        System.out.println();
        System.out.println("Inversions: " + inv1k + " (Time: " + (invEnd-invStart) + "ms)");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Sorting Time: " + (sortEnd-sortStart) + "ms");
    }

    public static void insertionSort(int[] array, int size) {
        comparisons = 0; 
        dataMoves = 0;
        int scan, unsortedValue;
        for (int index = 1; index < size; index++) {
            unsortedValue = array[index];
            dataMoves++; 
            scan = index;
            while (scan > 0) {
                comparisons++;
                if (array[scan - 1] <= unsortedValue) break;
                array[scan] = array[scan - 1];
                dataMoves++;
                scan--;
            }
            array[scan] = unsortedValue;
            dataMoves++;
        }
    }

    public static int countInversions(int[] array, int size) {
        int count = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (array[i] > array[j]) count++;
            }
        }
        return count;
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}
