Ex1: implement a java class generic MySimpleList that uses a doubly linked list to include the following operations : insertRear(e), removeRear(), removeElem(e),empty(), size(), and print().
Must set up your own doubly linked list using MyNode.
Try it out with a list of integers and a list of strings in a test driver. 
what is the runing  time for each operation (Big O notation)?

ADD THE following operation : insertElem(e1, e2), which inserts e1 before e2, if e2 is found in the list.
if e2 is not fuoud , then insert e1 to the front. 
Test with the following test case ba adding a statement below each comment:
// Create a MySimpleList object that holds a list of strings
// insert “Jane” to the rear
// insert “John” to the rear
// insert “Kim” to the rear
// print the list, should be Jane John Kim
// remove “Tom”, nothing removed
// remove “John”
// insert “Bob” before “Kim”
// remove rear
// insert “Jo” before “Jon” -- it is "Jane" and not "Jon"
// print the list, should be Jo Jane Bob
// print the size of the list, should be 3
// Create a MySimpleList object that holds a list of integers
// remove rear
// insert 5 before 7
// print the list, should be 5
// remove 5
// output “list is empty” or “list is not empty” using empty()
// operation, should be “list is empty”
// output running time for removeRear() O notation like O(1) or O(n)
// output running time for insertElem() O notation like O(1) or O(n)

Ex2: Given three sorted files of integers with lengths K , m, and n, provide a function/method that accepts the 3 sorted files to perform a three-way merge to merge them into one sorted file in O(K+m+n) time. The function/method has 4 parameters (3 input file and 1 output file).

Here is one test case to try: 
infile1.txt - 5 9 12
infile2.txt - 1 4 10 16 25
infile3.txt - 2 5 8 21

Result: 
 outfile.txt - 1 2 4 5 5 8 9 10 12 16 21 25

Instructions :  the function/method accepts the 3 unsorted files. 
will sort each input file in O(nlogn) time and then it performs a three-way merge to merge them into one sorted file in O(K+m+n) time. The function/method has 4 parameters (3 input file and 1 output file).   the program outputs first 5 values on one line, last 5 values on one line, and running time in ms. 

Sample format for screen output:
first 5 values: value1 value2 value3 value4 value5
last 5 values : value1 value2 value3 value4 value5 
time in ms : time

