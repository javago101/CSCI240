package PA1;

import static PA1.Ex1.countDuplicates;

import java.util.Random;

public class Ex2 {

    public static void main(String[] args) {
        Random rand = new Random();

        System.out.println("Author: Aiden Wang\n");
        System.out.println("| People | Count | Percent |");
        System.out.println("| ------ | ------ | ------- |");

        // Testing group sizes from 5 to 100 in increments of 5
        for (int people = 5; people <= 100; people += 5) {
            int sharedBirthdayCount = 0;

            // Run 100 experiments per group size
            for (int exp = 0; exp < 100; exp++) {
                int[] birthdays = new int[people];
                for (int i = 0; i < people; i++) {
                    birthdays[i] = rand.nextInt(365) + 1; // random day 1-365
                }

                // If there is at least one duplicate, a birthday is shared
                if (countDuplicates(birthdays) > 0) {
                    sharedBirthdayCount++;
                }
            }
            // Output the results. Because we run 100 experiments, count == percent.
            System.out.printf("| %-6d | %-5d | %d%%\n", people, sharedBirthdayCount, sharedBirthdayCount);
        }
    }
}
