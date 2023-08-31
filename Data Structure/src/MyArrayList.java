import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> {
    // 真正存储数据
    private E[] data;
    // 当前存储的实际元素个数，注意不是数组的长度
    private int size;
    // 默认初始容量
    private static final int INIT_CAPACITY = 1;

    public MyArrayList() {
        this(INIT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /************************工具方法**************************/
    // 获取大小
    public int size() {
        return size;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 判断索引是否合法
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    // 检查索引是否越界并抛出异常
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 将数组容量扩容到newCapacity大小
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        // 将原数组中的元素复制到新数组中
        System.arraycopy(data, 0, newData, 0, size);
        // 将新数组赋值给data
        data = newData;
    }

    /************************* 增 ****************************/

    // 在数组末尾添加元素
    public void addLast(E e) {
        if (data.length == size) {
            // 扩容到原来的2倍
            resize(2 * data.length);
        }
        data[size] = e;
        size++;
    }

    // 在数组指定位置添加元素
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (data.length == size) {
            // 扩容到原来的2倍
            resize(2 * data.length);
        }
        // 将index及其后面的元素向后移动一位
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    /************************* 删 ****************************/
    // 删除数组末尾的元素并返回
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size < data.length / 4) {
            resize(data.length / 2);
        }
        E removed_value = data[size - 1];
        data[size - 1] = null;
        size--;
        return removed_value;
    }

    public E remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size < data.length / 4) {
            resize(data.length / 2);
        }
        E removed_value = data[index];
        System.arraycopy(data, index + 1, data, index, size - (index + 1));
        // 注意这里把末尾删除
        data[size - 1] = null;
        size--;
        return removed_value;
    }

    public E removeFirst() {
        return remove(0);
    }

    /************************* 查 ****************************/
    // 返回末尾元素
    public E getBack() {
        return data[size - 1];
    }

    // 返回索引元素
    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    // 在一行内打印出所有元素，用空格分隔
    public void printMyList() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /************************* 改 ****************************/
    // 设定索引元素
    public E set(int index, E element) {
        checkElementIndex(index);
        E removed_element = data[index];
        data[index] = element;
        return removed_element;
    }

//    @Override
//    public Iterator<E> iterator() {
//        return new Iterator<E>() {
//            private int p = 0;
//
//            @Override
//            public boolean hasNext() {
//                return p != size;
//            }
//
//            @Override
//            public E next() {
//                return data[p++];
//            }
//        };
//    }
//
//    private void display() {
//        System.out.println("size = " + size + " cap = " + data.length);
//        System.out.println(Arrays.toString(data));
//    }


    public static void main(String[] args) {
        // 初始容量设置为 3
        MyArrayList<Integer> arr = new MyArrayList<>(3);

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
