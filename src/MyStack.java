public class MyStack<E> {
    MyLinkedList<E> list = new MyLinkedList<>();

    public void push(E e) {
        list.addLast(e);
    }

    public E pop() {
        return list.removeLast();
    }

    public E peek() {
        return list.getLast();
    }
}
