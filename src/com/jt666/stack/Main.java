package com.jt666.stack;

import java.util.Random;

public class Main {
    private static double testQueue(Stack<Integer> stack, int opCount) {
        long start = System.nanoTime();
        for (int i = 0; i <opCount ; i++) {
            stack.push(new Random().nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i <opCount ; i++) {
           stack.pop();
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000000;
        double time1 = testQueue(new ArrayStack<>(),opCount);
        double time2 = testQueue(new LinkedListStack<>(),opCount);
        System.out.println("ArrayStack: time:" +time1+"s");
        System.out.println("LinkedListStack: time:" +time2+"s");
    }
}
