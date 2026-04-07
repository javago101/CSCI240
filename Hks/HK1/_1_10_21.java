package HK1;

import java.util.Random;

public class _1_10_21 {
    /**
     * Shuffles an array of 52 integers manually.
     * This follows the Fisher-Yates algorithm.
     */
    public static void shuffle(int[] data) {
        Random rand = new Random();

        // Walk backwards through the array
        for (int i = data.length - 1; i > 0; i--) {
            // Pick a random index 'j' from 0 to i (inclusive)
            int j = rand.nextInt(i + 1);

            // Manually swap the elements at indices i and j
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    public static void main(String[] args) {
        // Create an array with integers 1 through 52
        int[] deck = new int[52];
        for (int i = 0; i < 52; i++) {
            deck[i] = i + 1;
        }

        // Perform the manual shuffle
        shuffle(deck);

        // Print the first few elements to verify the randomness
        System.out.println("First 5 elements after manual shuffle:");
        for (int i = 0; i < 5; i++) {
            System.out.print(deck[i] + " ");
        }
    }
}
