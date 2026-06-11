public class MySimpleList<T> {
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
