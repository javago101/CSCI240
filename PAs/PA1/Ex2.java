package PA1;

import static PA1.Ex1.countDuplicates;

import java.util.Random;

public class Ex2 {

    public static void main(String[] args) {

        // --- Testing Exercise 2 (Birthday Paradox) ---
        System.out.println("| People | Count | Percent |");
        System.out.println("| ------ | ------ | ------ |");

        Random rand = new Random();

        for (int people = 5; people <= 100; people += 5) {
            int sharedBirthdayCount = 0;

            // Run 100 experiments for each group size
            for (int exp = 0; exp < 100; exp++) {
                int[] birthdays = new int[people];
                for (int i = 0; i < people; i++) {
                    birthdays[i] = rand.nextInt(365) + 1; // Random day 1-365
                }

                // If there are any duplicates, at least two people share a birthday
                if (countDuplicates(birthdays) > 0) {
                    sharedBirthdayCount++;
                }
            }
            // Since we ran exactly 100 experiments, the count equals the percent
            System.out.printf("| %-6d | %-5d | %d%%\n", people, sharedBirthdayCount, sharedBirthdayCount);
        }
    }
}
