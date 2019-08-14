package com.jt666.queue;

public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        this.size = 0;
    }

    //默认容量10
    public Array() {
        this(10);

    }

    //获得数数组大小
    public int getSize() {
        return size;
    }

    //获得数组容量
    public int getCapacity() {
        return data.length;
    }

    //返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //向数组末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    //添加到数组前面
    public void addFirst(E e) {
        add(0, e);
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        if (size == data.length) //数组扩容
            resize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;

        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        E res = data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 &&data.length/2 != 0){
            resize(data.length / 2);
        }
        return res;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array:size = %d , capacity = %d \n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(data[i]);
            } else {
                sb.append(data[i]).append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

        Array<Integer> arr = new Array<Integer>();
        for (int i = 0; i < 10; i++)
            arr.addLast(i);
        System.out.println(arr);  // [0,1,2,3,4,5,6,7,8,9]

        arr.add(1, 100);
        System.out.println(arr);  // [0,100,1,2,3,4,5,6,7,8,9]

        arr.addFirst(-1);
        System.out.println(arr);
        // [-1, 0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        arr.remove(2);
        System.out.println(arr); //[-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        arr.removeElement(4);
        System.out.println(arr);  //[-1,0,1,2,3,5,6,7,8,9]

        arr.removeFirst();
        System.out.println(arr); //[0,1,2,3,5,6,7,8,9]
    }

}
