package PA4;

public class PA4_EC2 {
    // Author: Aiden Wang

    // Doubly linked node specifically for strings
    static class Node {
        String data;
        Node prev;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    // Custom Linked Deque Implementation
    static class MyLinkedDeque {
        private Node header;  // Sentinel node at the front
        private Node trailer; // Sentinel node at the rear
        private int size = 0;

        public MyLinkedDeque() {
            header = new Node(null);
            trailer = new Node(null);
            header.next = trailer;
            trailer.prev = header;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insertFront(String e) {
            Node newNode = new Node(e);
            newNode.next = header.next;
            newNode.prev = header;
            header.next.prev = newNode;
            header.next = newNode;
            size++;
        }

        public void insertRear(String e) {
            Node newNode = new Node(e);
            newNode.prev = trailer.prev;
            newNode.next = trailer;
            trailer.prev.next = newNode;
            trailer.prev = newNode;
            size++;
        }

        public String removeFront() {
            if (isEmpty()) return null;
            Node first = header.next;
            String res = first.data;
            header.next = first.next;
            first.next.prev = header;
            size--;
            return res;
        }

        public String removeRear() {
            if (isEmpty()) return null;
            Node last = trailer.prev;
            String res = last.data;
            trailer.prev = last.prev;
            last.prev.next = trailer;
            size--;
            return res;
        }

        public String getFront() {
            if (isEmpty()) return null;
            return header.next.data;
        }

        // 新增的打印方法：用于可视化当前 Deque 中的所有元素
        public void printDeque() {
            System.out.print(" -> Current Deque: [");
            Node curr = header.next;
            while (curr != trailer) {
                System.out.print(curr.data);
                curr = curr.next;
                if (curr != trailer) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by : Aiden Wang\n");

        MyLinkedDeque deque = new MyLinkedDeque();

        // Required Test Cases [2]
        System.out.println("Action: insert front (\"John\")");
        deque.insertFront("John");
        deque.printDeque(); // 打印状态

        System.out.println("\nAction: insert front (\"Jane\")");
        deque.insertFront("Jane");
        deque.printDeque(); // 打印状态

        System.out.println("\nAction: insert rear (\"Jo\")");
        deque.insertRear("Jo");
        deque.printDeque(); // 打印状态

        System.out.println("\nAction: remove rear");
        String removedRear = deque.removeRear();
        System.out.println(" -> Removed: " + removedRear);
        deque.printDeque(); // 打印状态

        System.out.println("\nAction: remove front");
        String removedFront = deque.removeFront();
        System.out.println(" -> Removed: " + removedFront);
        deque.printDeque(); // 打印状态

        System.out.println("\nAction: output size");
        System.out.println(" -> Size: " + deque.size());

        System.out.println("\nAction: output front item");
        System.out.println(" -> Front item: " + deque.getFront());
    }
}