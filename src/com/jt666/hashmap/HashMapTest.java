package com.jt666.hashmap;

public class HashMapTest {

    public static void main(String[] args) {
        MyHashMap<Object, Object> map = new MyHashMap<>();
        for (int i = 0; i < 200; i++) {
            map.put("k" + i, "v" + i);
        }
//        Object v = map.remove("k2");
//        System.out.println(v);

        System.out.println(map);

    }
}
