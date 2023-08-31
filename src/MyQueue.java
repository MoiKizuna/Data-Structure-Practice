public class MyQueue<E> {
    MyLinkedList<E> list = new MyLinkedList<>();

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public E peek() {
        return list.getFirst();
    }
}
