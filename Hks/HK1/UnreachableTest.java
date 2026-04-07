package HK1;

public class UnreachableTest {

    public static void main(String[] args) {
        System.out.println("Starting Test for Exercise 2.7.15...");

        TestUnreachable();

        System.out.println("Test Complete: Notice no compiler errors occurred!");
    }

    /**
     * Logic for Exercise 2.7.15
     */
    public static void TestUnreachable() {
        int a = 10;
        int b = 20;

        // Provably impossible: (10 + 20) is never 100.
        // The compiler lets this pass because it doesn't track
        // the values of variables 'a' and 'b' through the addition.
        if ((a + b) == 100) {
            System.out.println("This code is unreachable!");
        }

        // Another example using Math functions:
        // Math.abs always returns >= 0, so this can never be true.
        if (Math.abs(-5) < 0) {
            System.out.println("This is also unreachable!");
        }

        System.out.println("Method finished successfully.");
    }
}
