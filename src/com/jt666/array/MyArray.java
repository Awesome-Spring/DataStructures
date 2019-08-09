package com.jt666.array;

public class MyArray {
    public static void main(String[] args) {
        int [] arr = new int[10];
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = i;
        }
        int[] scores = new int[]{100,99,96};

        for (int i = 0; i <scores.length ; i++) {
            System.out.println(scores[i]);
        }
        scores[0] =99;
        System.out.println("=========================");
        for (int score : scores) {
            System.out.println(score);
        }
    }
}
