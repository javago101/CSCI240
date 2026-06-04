import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class PA12_EC2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang");
        System.out.println("Extra Credit 2: File I/O Performance (Input Tests)\n");

        int N = 98304;
        int[] arr = new int[N];
        Scanner sc = new Scanner(new File("large100k.txt"));
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        sc.close();

        // INPUT TESTS
        System.out.println("--- Read Tests ---");
        int[] readText = new int[N], readBin1 = new int[N], readBin256 = new int[N];

        long start = System.nanoTime();
        Scanner scRead = new Scanner(new File("output_text.txt"));
        for (int i = 0; i < N; i++) readText[i] = scRead.nextInt();
        scRead.close();
        System.out.println("a. Read text file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataInputStream dis1 = new DataInputStream(new FileInputStream("output_bin_1.dat"));
        for (int i = 0; i < N; i++) readBin1[i] = dis1.readInt();
        dis1.close();
        System.out.println("b. Read binary file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataInputStream dis2 = new DataInputStream(new FileInputStream("output_bin_256.dat"));
        byte[] readBuffer = new byte[256 * 4];
        for (int i = 0; i < N; i += 256) {
            dis2.readFully(readBuffer);
            ByteBuffer bb = ByteBuffer.wrap(readBuffer);
            for (int j = 0; j < 256; j++) readBin256[i + j] = bb.getInt();
        }
        dis2.close();
        System.out.println("c. Read binary file (256 chunked): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        System.out.println("\n--- Verification ---");
        System.out.println("Original array: First 5 = " + arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + ", " + arr[4] + " | Last 5 = " + arr[N-5] + ", " + arr[N-4] + ", " + arr[N-3] + ", " + arr[N-2] + ", " + arr[N-1]);
        System.out.println("Bin(256)read:   First 5 = " + readBin256[0] + ", " + readBin256[1] + ", " + readBin256[2] + ", " + readBin256[3] + ", " + readBin256[4] + " | Last 5 = " + readBin256[N-5] + ", " + readBin256[N-4] + ", " + readBin256[N-3] + ", " + readBin256[N-2] + ", " + readBin256[N-1]);
    }
}
