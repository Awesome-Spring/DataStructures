package com.jt666.heap;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data; //底层用动态数组实现

    public MaxHeap() {
        this.data = new Array<>();
    }

    //传入一个数组，构建成二叉堆
    public MaxHeap(E[] arr) {
        this.data = new Array<>(arr);
        //构建二叉堆
        heapify();
    }

    //构建二叉堆
    private void heapify() {
        //从最后一个 非叶子节点依次下沉
        int parent = parent(data.getSize() - 1);
        for (int index = parent; index >= 0; index--) {
            siftDown(index);
        }
    }

    // 返回堆中的元素个数
    public int getSize() {
        return data.getSize();
    }

    //判断堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    public int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("The Index Is Illegal!");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        //添加到数组末尾
        data.addLast(e);
        //末尾元素进行上浮操作
        siftUp(data.getSize() - 1);
    }

    //上浮，如果该处元素大于父元素，跟父元素交换位置，继续上浮，直至到顶点
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //查看堆中最大元素（堆顶位置）
    public E findMax() {
        if (isEmpty()) {
            throw new RuntimeException("The Heap Is Empty!");
        }
        return data.get(0);
    }

    //取出堆中最大元素
    public E extractMax() {
        E res = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return res;
    }

    /**
     * 下沉操作 判断要下沉元素是否右左孩子，如果右，则判断是否右右孩子，且左孩子和右孩子最大的是谁
     * 如果下沉元素小于左/右孩子 ，交换位置，继续下沉 直到没有左/右孩子 或要下沉的元素均大于左右孩子 下沉结束
     */
    private void siftDown(int k) {

        //有左孩子
        while (leftChild(k) < data.getSize()) {
            int i = leftChild(k); //左孩子索引
            //有右孩子 且左孩子处的值小于右孩子处的值
            if (i + 1 < data.getSize() && data.get(i).compareTo(data.get(i + 1)) < 0) {
                i++;//此时 data[i] 存储的是左右孩子中最大的
            }

            if (data.get(k).compareTo(data.get(i)) >= 0) {
                break;  //要下沉的节点均大于左右孩子 下沉结束
            }
            //否则，交换
            data.swap(k, i);
            k = i;//继续判断
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) {
        E res = findMax();
        data.set(0, e);
        //调整堆
        siftDown(0);
        return res;
    }

}
