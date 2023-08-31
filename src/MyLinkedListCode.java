import java.util.NoSuchElementException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
// https://leetcode.cn/problems/design-linked-list/
public class MyLinkedListCode {
    // 双链表节点
    private static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }

    // 占位符（哨兵节点）
    private final Node head, tail;
    private int size;

    // 构造函数初始化头尾节点
    public MyLinkedListCode() {
        this.head = new Node(0);
        this.tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    /************************工具方法**************************/
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

    // 按照索引查找元素（可优化）
    private Node getNode(int index) {
        Node temp_node = null;
        if (index < size / 2) {
            temp_node = head.next;
            for (int i = 0; i < index; i++) {
                temp_node = temp_node.next;
            }
        } else {
            temp_node = tail.prev;
            for (int i = size - 1; i > index; i--) {
                temp_node = temp_node.prev;
            }
        }

        return temp_node;
    }


    // 在一行内打印出所有元素，用空格分隔
    public void printMyList() {
        for (int i = 0; i < size; i++) {
            System.out.print(getNode(i).val + " ");
        }
        System.out.println();
    }

    /************************* 增 ****************************/
    public void addAtHead(int element) {
        Node e = new Node(element);
        Node first = head.next;

        e.prev = head;
        e.next = first;
        head.next = e;
        first.prev = e;

        size++;
    }

    public void addAtTail(int element) {
        Node e = new Node(element);
        Node last = tail.prev;

        e.prev = last;
        e.next = tail;
        last.next = e;
        tail.prev = e;

        size++;
    }

    public int addAtIndex(int index, int element) {
        if (!isPositionIndex(index)) {
            return -1;
        }
        // 可以优化速度
        if (index == size) {
            addAtTail(element);
            return 0;
        }
        Node node_next = getNode(index);
        Node node_prev = node_next.prev;
        Node e = new Node(element);

        e.prev = node_prev;
        e.next = node_next;
        node_next.prev = e;
        node_prev.next = e;

        size++;
        return 0;
    }

    /************************* 删 ****************************/

    public int deleteAtIndex(int index) {
        if (!isElementIndex(index)) {
            return -1;
        }
        if (isEmpty()) {
            return -1;
        }
        Node node = getNode(index);

        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;

        size--;
        return node.val;
    }

    /************************* 查 ****************************/

    public int get(int index) {
        if (!isElementIndex(index)) {
            return -1;
        }
        if (isEmpty()) {
            return -1;
        }
        return getNode(index).val;
    }

    public static void main(String[] args) {
        // 初始容量设置为 3
        MyLinkedListCode arr = new MyLinkedListCode();

        arr.addAtHead(2);
        arr.printMyList();
        arr.deleteAtIndex(1);
        arr.printMyList();
        arr.addAtHead(1);
        arr.addAtHead(2);
        arr.addAtHead(7);
        arr.addAtHead(3);
        arr.addAtHead(2);
        arr.addAtTail(5);
        arr.get(5);
        arr.printMyList();
        arr.deleteAtIndex(6);
        arr.deleteAtIndex(4);
        arr.printMyList();

    }
}

