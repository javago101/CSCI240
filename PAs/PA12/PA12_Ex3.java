import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class PA12_Ex3 {
    public static void main(String[] args) throws Exception {
        System.out.println("Author: Aiden Wang");
        System.out.println("Exercise 3: File I/O Performance (Output Tests)\n");

        int N = 98304;
        int[] arr = new int[N];
        Scanner sc = new Scanner(new File("large100k.txt"));
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        sc.close();

        // OUTPUT TESTS
        System.out.println("--- Write Tests ---");
        long start = System.nanoTime();
        PrintWriter pw = new PrintWriter(new FileWriter("output_text.txt"));
        for (int i = 0; i < N; i++) pw.println(arr[i]);
        pw.close();
        System.out.println("a. Write text file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataOutputStream dos1 = new DataOutputStream(new FileOutputStream("output_bin_1.dat"));
        for (int i = 0; i < N; i++) dos1.writeInt(arr[i]);
        dos1.close();
        System.out.println("b. Write binary file (one by one): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        DataOutputStream dos2 = new DataOutputStream(new FileOutputStream("output_bin_256.dat"));
        ByteBuffer buffer = ByteBuffer.allocate(256 * 4);
        for (int i = 0; i < N; i += 256) {
            buffer.clear();
            for (int j = 0; j < 256; j++) buffer.putInt(arr[i + j]);
            dos2.write(buffer.array());
        }
        dos2.close();
        System.out.println("c. Write binary file (256 chunked): " + (System.nanoTime() - start) / 1_000_000.0 + " ms");
    }
}
