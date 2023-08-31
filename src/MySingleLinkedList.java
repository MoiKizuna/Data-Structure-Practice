import java.util.NoSuchElementException;

public class MySingleLinkedList<E> {
    private static class Node<E> {
        E val;
        Node<E> next;

        Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    public MySingleLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private Node<E> getNode(int index) {
        Node<E> temp_node = head.next;

        for (int i = 0; i < index; i++) {
            temp_node = temp_node.next;
        }

        return temp_node;
    }

    public void printMyList() {
        for (int i = 0; i < size; i++) {
            System.out.print(getNode(i).val + " ");
        }
        System.out.println();
    }

    public void addAtHead(E element) {
        Node<E> e = new Node<>(element);
        if (isEmpty()) {
            head.next = e;
            e.next = tail;
            size++;
        } else {
            Node<E> first = head.next;

            e.next = first;
            head.next = e;

            size++;
        }
    }

    public void addAtTail(E element) {
        Node<E> e = new Node<>(element);
        if (isEmpty()) {
            addAtHead(element);
        } else {
            Node<E> last_prev = getNode(size - 1);

            e.next = tail;
            last_prev.next = e;

            size++;
        }
    }

    public void addAtIndex(int index, E element) {
        if (!isPositionIndex(index)) {
            return;
        }
        if (index == 0) {
            addAtHead(element);
        } else {
            Node<E> node_prev = getNode(index - 1);
            Node<E> node_next = node_prev.next;
            Node<E> e = new Node<>(element);

            e.next = node_next;
            node_prev.next = e;

            size++;
        }
    }

    public E deleteAtIndex(int index) {
        if (!isElementIndex(index) || isEmpty()) {
            return null;
        }
        if (index == 0) {
            return deleteFirst();
        }
        Node<E> node_prev = getNode(index - 1);
        Node<E> node = node_prev.next;

        node_prev.next = node.next;
        node.next = null;

        size--;
        return node.val;
    }

    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = head.next;

        head.next = first.next;
        first.next = null;

        size--;
        return first.val;
    }

    public E get(int index) {
        if (!isElementIndex(index) || isEmpty()) {
            return null;
        }
        return getNode(index).val;
    }

    public static void main(String[] args) {
        MySingleLinkedList<Integer> arr = new MySingleLinkedList<>();

        arr.addAtTail(1);
        arr.printMyList();
        arr.get(0);
        arr.printMyList();
    }
}
