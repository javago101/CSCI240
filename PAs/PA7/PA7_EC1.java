package PA7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PA7_EC1 {
    public static void main(String[] args) {
        System.out.println("Modified by: Aiden Wang\n");

        String filePath = "PAs/PA7/Data/USDeclIndFormatted.txt";
        Set<String> uniqueWords = new HashSet<>();

        // Read all unique words
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                uniqueWords.add(scanner.next()); 
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + filePath);
            System.out.println("Make sure you are running from the project root.");
            return;
        }

        System.out.println("Unique words extracted from US Declaration of Independence: " + uniqueWords.size());
        System.out.println("\n=== Extra Credit 1: Cyclic-Shift Hash Collision Rates ===");

        // Tests specified by the prompt
        int[] shifts = {0, 1, 5, 13};

        for (int shift : shifts) {
            int collisions = countCollisions(uniqueWords, shift);
            System.out.println("Cyclic Shift Parameter [shift = " + shift + "] -> Produced Collisions: " + collisions);
        }
        
        System.out.println("\nConclusion: A shift of '0' produces an immense sum of collisions exactly because it fails to physically shift/mix the bits across positions, equivalent to simple character addition.");
    }

    private static int countCollisions(Set<String> words, int shift) {
        Set<Integer> hashCodesSeen = new HashSet<>();
        int collisions = 0;

        for (String word : words) {
            int hash = 0;
            for (int i = 0; i < word.length(); i++) {
                hash = (hash << shift) | (hash >>> (32 - shift)); // Core cyclic shift
                hash += word.charAt(i); 
            }
            
            // If another completely different word already produced this exact integer, log collision
            if (!hashCodesSeen.add(hash)) {
                collisions++;
            }
        }

        return collisions;
    }
}
