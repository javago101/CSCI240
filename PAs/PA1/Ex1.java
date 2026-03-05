/* Java Class:
Author:Aiden Wang
Class: CSCI 240
Date:
Description:
I certify that the code below is my own work.
Exception(s): N/A
*/

package PA1;

class Ex1 {
    // Accepts an array of int values and returns the count of duplicate pairs
    public static int countDuplicates(int[] arr) {
        int count = 0;
        // Compare each element with every element that comes after it
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] test1 = {5, 7, 2, 6, 4};
        int[] test2 = {5, 7, 2, 2, 5, 6, 4, 2};

        System.out.println("Test 1 (5, 7, 2, 6, 4) result: " + countDuplicates(test1));
        System.out.println("Test 2 (5, 7, 2, 2, 5, 6, 4, 2) result: " + countDuplicates(test2));
    }
}

