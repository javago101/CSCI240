public class Ex1Test_1 {
    public static void main(String[] args) {

        System.out.println("Modified by : Aiden Wang \n I certify that I did my own work with no outside help.\n");

        // Create a MySimpleList object that holds a list of strings
        MySimpleList<String> listStr = new MySimpleList<>();

        // insert "Jane" to the rear
        listStr.insertRear("Jane");
        // insert "John" to the rear
        listStr.insertRear("John");
        // insert "Kim" to the rear
        listStr.insertRear("Kim");

        // print the list , should be Jane John Kim
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

class MyNode<T> {
    public T data;
    public MyNode<T> prev;
    public MyNode<T> next;

    public MyNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class MySimpleList<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MySimpleList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertRear(T e) {
        MyNode<T> newNode = new MyNode<>(e);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void removeRear() {
        if (tail == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void removeElem(T e) {
        MyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(e)) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = head.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return; // Remove only first occurrence
            }
            current = current.next;
        }
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void print() {
        MyNode<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void insertElem(T e1, T e2) {
        MyNode<T> current = head;
        boolean found = false;

        while (current != null) {
            if (current.data.equals(e2)) {
                found = true;
                break;
            }
            current = current.next;
        }

        MyNode<T> newNode = new MyNode<>(e1);

        if (!found) {
            // insert at front
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        } else {
            // insert before current
            if (current == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
            size++;
        }
    }
}
