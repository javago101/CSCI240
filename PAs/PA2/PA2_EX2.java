/* Java Class:
Author:Aiden Wang
Class: CSCI 240
Date:
Description:
I certify that the code below is my own work.
Exception(s): N/A
*/

package PA2;
class PA2_Ex2 {

    static int recursiveCalls = 0;

    public static void findPair(int[] A, int k, int low, int high) {
        recursiveCalls++;

        // Base case: If bounds cross, no pair exists
        if (low >= high) {
            System.out.println("Result: no pair exists");
            return;
        }

        int sum = A[low] + A[high];

        if (sum == k) {
            System.out.println("Result: values " + A[low] + " and " + A[high]);
        } else if (sum < k) {
            findPair(A, k, low + 1, high); // Need a larger sum
        } else {
            findPair(A, k, low, high - 1); // Need a smaller sum
        }
    }

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang\n");
        int[] A = {3, 9, 12, 15, 16, 23};

        // Test Case 1
        System.out.println("Test Case: k = 25");
        recursiveCalls = 0;
        findPair(A, 25, 0, A.length - 1);
        System.out.println("Recursive calls: " + recursiveCalls + "\n");

        // Test Case 2
        System.out.println("Test Case: k = 16");
        recursiveCalls = 0;
        findPair(A, 16, 0, A.length - 1);
        System.out.println("Recursive calls: " + recursiveCalls);
    }
}