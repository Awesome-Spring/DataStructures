package com.jt666.linkedlist;

public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

    private int size; //链表的大小
    private Node dummyHead;  //虚拟头结点 LinkedList:[dummyHead]-->null


    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    /**
     * 获得链表存储的个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表头部添加
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表末尾添加
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 只指定index处添加元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 获得指定index处的节点元素
     *
     * @param index
     * @return
     */
    public E get(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur.e;
    }

    /**
     * 判断是否包含指定元素元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除指定位置的节点
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    /**
     * 删除头结点
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除聊表末尾元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 修改指定位置节点上的元素
     * @param index
     * @param e
     */
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        cur.e = e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList:[dummyHead]-->");
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.e + "-->");
            cur = cur.next;
        }
        sb.append("null");
        return sb.toString();
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i, i);
            System.out.println(list);
        }
        list.addLast(10);
        list.addFirst(10);

        list.removeLast();
        list.removeFirst();
        System.out.println(list);

//        System.out.println(list.contains(11));
    }
}
