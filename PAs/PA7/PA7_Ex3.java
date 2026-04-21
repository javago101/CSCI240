package PA7;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PA7_Ex3 {
    public static void main(String[] args) throws Exception {
        System.out.println("Modified by: Aiden Wang\n");

        String path = "PAs/PA7/Data/USDeclIndFormatted.txt";
        Set<String> words = new HashSet<>();

        // Load all unique words from the file
        Scanner sc = new Scanner(new File(path));
        while (sc.hasNext()) {
            words.add(sc.next());
        }
        sc.close();

        System.out.println("Unique words: " + words.size());

        int[] aValues = {1, 37, 40, 41};

        for (int a : aValues) {
            Set<Integer> seen = new HashSet<>();
            int collisions = 0;

            for (String w : words) {
                int h = 0;
                for (int i = 0; i < w.length(); i++) {
                    h = h * a + w.charAt(i);
                }
                
                if (!seen.add(h)) {
                    collisions++;
                }
            }
            System.out.println("Parameter a = " + a + " -> Collisions: " + collisions);
        }
    }
}
