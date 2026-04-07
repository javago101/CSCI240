package PA2;

// Author: Aiden Wang
class PA2_EX4 {
    // Setup Node
    static class Node {
        String data;
        Node next;
        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    // Setup global/static variables
    static Node head = null;
    static int numElems = 0;

    public static void insertFront(String name) {
        Node newNode = new Node(name);
        newNode.next = head;
        head = newNode;
        numElems++;
    }

    public static void insertRear(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        numElems++;
    }

    public static void removeElem(String name) {
        if (head == null) {
            System.out.println("Unsuccessful");
            return;
        }
        // If the element to remove is at the head
        if (head.data.equals(name)) {
            head = head.next;
            numElems--;
            System.out.println("Successful");
            return;
        }
        // Search for the element
        Node curr = head;
        while (curr.next != null && !curr.next.data.equals(name)) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
            numElems--;
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    public static void printAll() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Author: Aiden Wang\n");

        insertFront("Jo");
        insertRear("Jane");
        insertFront("John");
        insertRear("Kim");

        System.out.println("numElems: " + numElems);  // output 4

        removeElem("Jane");  // successful
        removeElem("Bob");   // unsuccessful

        System.out.print("List: ");
        printAll();    // output John Jo Kim
    }
}