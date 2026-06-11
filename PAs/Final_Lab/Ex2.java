import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Ex2 {

    public static void main(String[] args) {
        System.out.println("Modified by : Aiden Wang \n I certify that I did my own work with no outside help.\n");
        String file1 = "PAs/Final_Lab/data/infile1.txt";
        String file2 = "PAs/Final_Lab/data/infile2.txt";
        String file3 = "PAs/Final_Lab/data/infile3.txt";
        String outFile = "PAs/Final_Lab/data/outfile.txt";

        threeWayMerge(file1, file2, file3, outFile);
    }

    public static void threeWayMerge(String f1, String f2, String f3, String outF) {
        long startTime = System.currentTimeMillis();

        ArrayList<Integer> list1 = readAndSort(f1);
        ArrayList<Integer> list2 = readAndSort(f2);
        ArrayList<Integer> list3 = readAndSort(f3);

        ArrayList<Integer> merged = new ArrayList<>();

        int i = 0, j = 0, k = 0;

        // O(K + m + n) merge step
        while (i < list1.size() || j < list2.size() || k < list3.size()) {
            int v1 = Integer.MAX_VALUE;
            if (i < list1.size()) {
                v1 = list1.get(i);
            }

            int v2 = Integer.MAX_VALUE;
            if (j < list2.size()) {
                v2 = list2.get(j);
            }

            int v3 = Integer.MAX_VALUE;
            if (k < list3.size()) {
                v3 = list3.get(k);
            }

            int min = v1;
            if (v2 < min) {
                min = v2;
            }
            if (v3 < min) {
                min = v3;
            }

            merged.add(min);

            if (min == v1) {
                i++;
            } else if (min == v2) {
                j++;
            } else if (min == v3) {
                k++;
            }
        }

        // Save the sorted values into the output file
        try {
            PrintWriter pw = new PrintWriter(new File(outF));
            for (int x = 0; x < merged.size(); x++) {
                pw.print(merged.get(x) + " ");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Output file error: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;

        // Screen output format
        System.out.print("first 5 values: ");
        int count = 0;
        for (int x = 0; x < merged.size(); x++) {
            System.out.print(merged.get(x) + " ");
            count++;
            if (count == 5) {
                break;
            }
        }
        System.out.println();

        System.out.print("last 5 values : ");
        int startLast = merged.size() - 5;
        if (startLast < 0) {
            startLast = 0;
        }
        for (int x = startLast; x < merged.size(); x++) {
            System.out.print(merged.get(x) + " ");
        }
        System.out.println();

        System.out.println("time in ms : " + runTime);
    }

    private static ArrayList<Integer> readAndSort(String filename) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

        // O(n log n) sorting
        Collections.sort(list);

        return list;
    }
}
