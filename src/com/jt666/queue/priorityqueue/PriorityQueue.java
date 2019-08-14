package com.jt666.queue.priorityqueue;

import com.jt666.queue.Queue;

public class PriorityQueue<E extends  Comparable<E>> implements Queue<E> {

    private  MaxHeap<E> heap;

    public PriorityQueue() {
        heap = new MaxHeap();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);

    }

    @Override
    public E dequeue() {
       return heap.extractMax();

    }

    @Override
    public E getFront() {
        return heap.findMax();
    }
}
