package HK1;

public class _1_10_6 {
    public static int sumOddPositives(int n) {
        int sum = 0;
        // Iterate from 1 up to n, stepping by 2 to hit only odd numbers
        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Testing Exercise 1.10.6:");
        System.out.println("n = 5 (Expected 9): " + sumOddPositives(5));
        System.out.println("n = 4 (Expected 4): " + sumOddPositives(4));
        System.out.println("n = 1 (Expected 1): " + sumOddPositives(1));
        System.out.println();
    }
}
