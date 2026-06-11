public class Ex1Test {
    public static void main(String[] args) {

        System.out.println("Modified by : Aiden Wang \n I certify that I did my own work with no outside help.\n");

        // Create a MySimpleList object that holds a list of strings
        MySimpleList<String> listStr = new MySimpleList<>();

        // insert "Jane" to the rear
        listStr.insertRear("Jane");
        // insert "john" to the rear
        listStr.insertRear("John");
        // insert "kim" to the rear
        listStr.insertRear("Kim");

        // print the list , shoud=ld be Jane John Kim
        listStr.print();

        // remove "Tom", nothing removed
        listStr.removeElem("Tom");

        // remove "John"
        listStr.removeElem("John");
        // insert "Bob" before "Kim"
        listStr.insertElem("Bob", "Kim");

        // remove rear
        listStr.removeRear();

        // insert "Jo" before "Jane" (typo in prompt fixed from "Jon" to "Jane")
        listStr.insertElem("Jo", "Jane");

        // print the list , should be Jo Jane Bob
        listStr.print();

        // Print the size of the list , should be 3
        System.out.println(listStr.size());

        System.out.println();

        // Create a MySimpleList object that holds a list of integers
        MySimpleList<Integer> listInt = new MySimpleList<>();

        // remove rear
        listInt.removeRear();

        // insert 5 before 7
        listInt.insertElem(5, 7);

        // print the list , should be 5
        listInt.print();

        // remove 5
        listInt.removeElem(5);

        // output "list is empty" or "list is not empty" using empty() operation ,
        // should be "list is empty
        if (listInt.empty()) {
            System.out.println("list is empty");
        } else {
            System.out.println("list is not empty");
        }
        System.out.println();

        // output running time for removeRear() big O notation like O(1) or O(n)
        System.out.println("removeRear() running time: O(1)");

        // output running time for insertElem() big O notation like O(1) or O(n)
        System.out.println("insertElem() running time: O(n)");
    }
}
