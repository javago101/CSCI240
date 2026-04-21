package PA7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PA7_Ex3 {
    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        String filePath = "PAs/PA7/Data/USDeclIndFormatted.txt";
        Set<String> uniqueWords = new HashSet<>();

        // 1. Read all fully unique words across the whole file
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                uniqueWords.add(scanner.next()); 
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + filePath);
            System.out.println("Make sure you are running from the project root.");
            return;
        }

        System.out.println("Total unique words extracted from US Declaration of Independence: " + uniqueWords.size());
        System.out.println("\n=== Comparative Analysis of Polynomial Hash Collision Rates ===");

        // Tests specified by the prompt
        int[] aValues = {1, 37, 40, 41};

        for (int a : aValues) {
            int collisions = countCollisions(uniqueWords, a);
            System.out.println("Polynomial Hash Parameter [a = " + a + "] -> Produced Collisions: " + collisions);
        }
        
        System.out.println("\nConclusion: As expected, 'a = 1' produces a massive amount of collisions compared to 37, 40, or 41.");
        System.out.println("This is because an 'a' parameter of 1 essentially degrades the polynomial hash to a simple summation of ASCII characters.");
    }

    private static int countCollisions(Set<String> words, int a) {
        Set<Integer> hashCodesSeen = new HashSet<>();
        int collisions = 0;

        for (String word : words) {
            int hash = 0;
            // Native horners computation using standard 32-bit int overflow mechanism
            for (int i = 0; i < word.length(); i++) {
                hash = hash * a + word.charAt(i);
            }
            
            // If the integer hash is already in the set from a previous differing string, log a collision
            if (!hashCodesSeen.add(hash)) {
                collisions++;
            }
        }

        return collisions;
    }
}
