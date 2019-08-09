package com.jt666.hashmap;

import java.util.Arrays;

public class MyHashMap<K, V> {
    //Map的大小
    private int size;
    //默认hash桶的容量
    private static int CAPACITY = 16;
    //hash桶
    Node<K, V>[] table;

    private class Node<K, V> {
        int hash;
        K k;
        V v;
        Node<K, V> next;

        public Node(int hash, K k, V v) {
            this.hash = hash;
            this.k = k;
            this.v = v;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    ", next=" + next +
                    '}';
        }
    }

    public MyHashMap() {
        table = new Node[CAPACITY];
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "size=" + size +
                ", table=" + Arrays.toString(table) +
                '}';
    }

    public V put(K key, V value) {
        //首先通过hash算法，找到索引
        int hash = (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16); //扰动函数
        int index = hash & (table.length - 1); //保证index长度在0 - table.length 之间，防止索引越界 (前提：容量为2的次幂)
        //判断table[index] 是否为空
        if (table[index] == null) {
            table[index] = new Node<K, V>(hash, key, value);//创建链表节点放在该处
            size++;
            return null;
        }

        //判断是否存在相同的key
        for (Node<K, V> node = table[index]; node != null; node = node.next) {
            if (node.hash == key.hashCode() && node.k.equals(key)) {
                V oldValue = node.v;
                node.v = value;
                return oldValue;
            }
        }

        //没有相同key，添加到链表的末尾
        addLast(table[index], key, value);

        return null;
    }

    /**
     * @param head  头结点
     * @param key
     * @param value
     */
    private void addLast(Node<K, V> head, K key, V value) {
        while (head.next != null) {
            head = head.next;
        }
        head.next = new Node<K, V>(key.hashCode(), key, value);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        int index = key.hashCode() & (table.length - 1);
        K k;
        for (Node<K, V> node = table[index]; node != null; node = node.next) {
            if (node.hash == key.hashCode() &&
                    ((k = node.k) == key || (key != null && key.equals(k)))) {
                return node.v;
            }
        }
        return null;
    }

    public V remove(Object key) {
        int index = key.hashCode() & (table.length - 1);
        K k;
        V v = null;
        Node<K, V> head = table[index];
        Node<K, V> pre = null;

        //第一个节点匹配，直接返回
        if (head.next == null && head.hash == key.hashCode() && ((k = head.k) == key || (key != null && key.equals(k)))) {
            v = head.v;
            head = null;
            size--;
            return v;
        }

        //遍历链表(从第二个开始)
        do {
            if (head.next.hash == key.hashCode() &&
                    ((k = head.next.k) == key || (key != null && key.equals(k)))) {
                pre = head;
                v = head.next.v;
                pre.next = pre.next.next;
                size--;
                break;
            } else {
                head = head.next;
            }
        } while (head != null);

        return v;
    }

}
