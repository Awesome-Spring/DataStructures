package com.jt666.queue;

public class LinkedListQueue<E> implements Queue<E> {
    /**
     * 节点类
     * E e 数据
     * Node next 下个节点
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private int size;
    private Node head, tail; //指向头部的节点和指向尾部的节点

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队操作，在链表的尾部添加
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        if (tail == null) { //尾部节点为空，说明整个链表没有元素，添加元素，此时链表存才一个几点头节点和尾节点指向同一个
            tail = new Node(e);
            head = tail;
        } else { //不为空，则插入到链表后面，更新尾节点
             Node node =  new Node(e);
             tail.next = node;
             tail = node;
        }
        size++;
    }

    /**
     * 出对操作，在链表头部删除
     * @return
     */
    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        Node delNode = head; //要删除的结点
        head = head.next;
        if (head==null){ //删除完，头结点为空，则链表为空，相应尾节点也应该为空
            tail =null;
        }
        size--;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }


    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
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
