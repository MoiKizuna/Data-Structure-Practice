import java.util.NoSuchElementException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class MyLinkedList<E> {
    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // 占位符（哨兵节点）
    private final Node<E> head, tail;
    private int size;

    // 构造函数初始化头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
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

    // 检查位置或者索引是否可以存在元素
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new NoSuchElementException();
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new NoSuchElementException();
        }
    }

    // 按照索引查找元素（可优化）
    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> temp_node = null;
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
    public void addFirst(E element) {
        Node<E> e = new Node<>(element);
        Node<E> first = head.next;

        e.prev = head;
        e.next = first;
        head.next = e;
        first.prev = e;

        size++;
    }

    public void addLast(E element) {
        Node<E> e = new Node<>(element);
        Node<E> last = tail.prev;

        e.prev = last;
        e.next = tail;
        last.next = e;
        tail.prev = e;

        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        // 可以优化速度
        if (index == size) {
            addLast(element);
            return;
        }
        Node<E> node_next = getNode(index);
        Node<E> node_prev = node_next.prev;
        Node<E> e = new Node<>(element);

        e.prev = node_prev;
        e.next = node_next;
        node_next.prev = e;
        node_prev.next = e;

        size++;
    }

    /************************* 删 ****************************/
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // 虚拟节点的存在让我们不需要考虑空指针的问题
        Node<E> first = head.next;

        head.next = first.next;
        first.next.prev = head;
        first.next = null;
        first.prev = null;

        size--;
        return first.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> last = tail.prev;

        tail.prev = last.prev;
        last.prev.next = tail;
        last.prev = null;
        last.next = null;

        size--;
        return last.val;
    }

    public E remove(int index) {
        checkElementIndex(index);
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> node = getNode(index);

        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;

        size--;
        return node.val;
    }

    /************************* 查 ****************************/
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    public E get(int index) {
        checkElementIndex(index);
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(index).val;
    }

    /************************* 改 ****************************/
    public E setFirst(E element) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> first = head.next;
        E old_val = first.val;
        first.val = element;
        return old_val;
    }

    public E setLast(E element) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> last = tail.prev;
        E old_val = last.val;
        last.val = element;
        return old_val;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> node = getNode(index);
        E old_val = node.val;
        node.val = element;
        return old_val;
    }

    public static void main(String[] args) {
        // 初始容量设置为 3
        MyLinkedList<Integer> arr = new MyLinkedList<>();

        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
            arr.addLast(i);
        }
        arr.printMyList();
        arr.remove(3);
        arr.printMyList();
        arr.add(1, 9);
        arr.printMyList();
        arr.addFirst(100);
        arr.printMyList();
        int val = arr.removeLast();

    }
}
