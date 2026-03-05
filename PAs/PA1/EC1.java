/* Java Class:
Author:Aiden Wang
Class: CSCI 240
Date:
Description:
I certify that the code below is my own work.
Exception(s): N/A
*/

package PA1;

import java.util.Random;

public class EC1 {

    public static void main(String[] args) {

        System.out.println("Author: Aiden Wang\n");

        String baseSentence = "I will never spam my friends again.";
        int numLines = 50;
        int numMistakes = 10;

        String[] sentences = new String[numLines];
        int[] mistakeCounts = new int[numLines];
        for (int i = 0; i < numLines; i++) {
            sentences[i] = baseSentence;
        }

        Random rand = new Random();
        // 1. Distribute exactly 10 mistakes randomly across the 50 lines
        for (int i = 0; i < numMistakes; i++) {
            mistakeCounts[rand.nextInt(numLines)]++;
        }

        // 2. Apply the mistakes
        for (int i = 0; i < numLines; i++) {
            if (mistakeCounts[i] > 0) {
                char[] chars = sentences[i].toCharArray();
                for (int m = 0; m < mistakeCounts[i]; m++) {
                    int charIdx;
                    // Find a valid letter to replace (ignores spaces and punctuation)
                    do {
                        charIdx = rand.nextInt(chars.length);
                    } while (!Character.isLetter(chars[charIdx]));

                    // Generate a random different letter
                    char oldChar = chars[charIdx];
                    char newChar;
                    do {
                        newChar = (char) ('a' + rand.nextInt(26));
                    } while (newChar == Character.toLowerCase(oldChar));

                    // Preserve the case if the original letter was uppercase
                    if (Character.isUpperCase(oldChar)) {
                        newChar = Character.toUpperCase(newChar);
                    }

                    chars[charIdx] = newChar; // Insert the typo
                }
                // Append the count to the end of the line
                sentences[i] = new String(chars) + " (" + mistakeCounts[i] + " mistakes)";
            }

            // 3. Output the sentence
            // 4. COMPACT 2-COLUMN OUTPUT
            // %-52s forces the sentence to take up 52 characters of space to align the columns
            System.out.printf("%2d: %-52s", i + 1, sentences[i]);

            // Every 2nd sentence, move to a new line. Otherwise, print a separator.
            if ((i + 1) % 2 == 0) {
                System.out.println();
            } else {
                System.out.print(" |  ");
            }
        }
    }
}
