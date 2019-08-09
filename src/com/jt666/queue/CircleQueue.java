package com.jt666.queue;

public class CircleQueue<E> implements Queue<E> {

    private E[] data;
    private int front; //头指针
    private int tail;  //尾指针
    private int size; // (tail - front +data.length)% data.length

    public CircleQueue(int capacity) {
        this.data = (E[]) new Object[capacity + 1]; //有意浪费一个空间  tail|  |front| x | x | x | x |
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public CircleQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(2 * getCapacity());
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;

    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i+front];
        }
        data = newData;
        front =0;
        tail =size;
    }

    @Override
    public E dequeue() {
        E res = data[front];
        data[front]=null;
        front = (front+1)%data.length;
        size--;
        if (size==getCapacity()/4 &&getCapacity()/2!=0) {
            resize(getCapacity()/2);
        }
        return res;
    }

    @Override
    public E getFront() {
        return data[front];
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        sb.append("front[");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i+1)%data.length!=tail){ //不是最后一个元素
                sb.append(",");
            }
        }

        sb.append("]tail");
        return sb.toString();
    }


    public static void main(String[] args){

        CircleQueue<Integer> queue = new CircleQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}

