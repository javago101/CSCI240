public class MyNode<T> {
    public T data;
    public MyNode<T> prev;
    public MyNode<T> next;

    public MyNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
