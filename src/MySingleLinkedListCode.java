import java.util.NoSuchElementException;

public class MySingleLinkedListCode {
    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private final Node head, tail;
    private int size;

    public MySingleLinkedListCode() {
        this.head = new Node(0);
        this.tail = new Node(0);
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

    private Node getNode(int index) {
        Node temp_node = head.next;

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

    public void addAtHead(int element) {
        Node e = new Node(element);
        if (isEmpty()) {
            head.next = e;
            e.next = tail;
            size++;
            return;
        } else {
            Node first = head.next;

            e.next = first;
            head.next = e;

            size++;
        }
    }

    public void addAtTail(int element) {
        Node e = new Node(element);
        if (isEmpty()) {
            addAtHead(element);
        } else {
            Node last_prev = getNode(size - 1);

            e.next = tail;
            last_prev.next = e;

            size++;
        }
    }

    public int addAtIndex(int index, int element) {
        if (!isPositionIndex(index)) {
            return -1;
        }
        if (index == 0) {
            addAtHead(element);
            return 0;
        } else {
            Node node_prev = getNode(index - 1);
            Node node_next = node_prev.next;
            Node e = new Node(element);

            e.next = node_next;
            node_prev.next = e;

            size++;
            return 0;
        }
    }

    public int deleteAtIndex(int index) {
        if (!isElementIndex(index) || isEmpty()) {
            return -1;
        }
        if (index == 0) {
            return deleteFirst();
        }
        Node node_prev = getNode(index - 1);
        Node node = node_prev.next;

        node_prev.next = node.next;
        node.next = null;

        size--;
        return node.val;
    }

    public int deleteFirst() {
        if (isEmpty()) {
            return -1;
        }
        Node first = head.next;

        head.next = first.next;
        first.next = null;

        size--;
        return first.val;
    }

    public int get(int index) {
        if (!isElementIndex(index) || isEmpty()) {
            return -1;
        }
        return getNode(index).val;
    }

    public static void main(String[] args) {
        MySingleLinkedListCode arr = new MySingleLinkedListCode();

        arr.addAtTail(1);
        arr.printMyList();
        arr.get(0);
        arr.printMyList();
    }
}
