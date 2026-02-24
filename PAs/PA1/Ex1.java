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
// Ex 1: count duplicate pairs

    public static int countDuplicates(int[] arr){
        int count = 0;

        // compare each element with every element that comes after it
        for (int i = 0; i < arr.length; i++ ){
            for (int j = i + 1; j < arr.length; j++){
                if(arr[i] == arr[j]){
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args){

        // testing case for Ex1

        int[] case1 = {5,7,2,6,4};
        int[] case2 = {5,7,2,2,5,6,4,2};

       System.out.println("test 1 (expected 0):" + countDuplicates(case1));

       System.out.println("test 2 (expected 4):" + countDuplicates(case2));
    }

}
