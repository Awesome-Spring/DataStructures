package com.jt666.queue;

import java.util.Random;

public class Main {
    private static double testQueue(Queue<Integer> queue, int opCount) {
        long start = System.nanoTime();
        for (int i = 0; i <opCount ; i++) {
            queue.enqueue(new Random().nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i <opCount ; i++) {
           queue.dequeue();
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000;
        double time1 = testQueue(new ArrayQueue<Integer>(),opCount);
        double time2 = testQueue(new LoopQueue<Integer>(),opCount);
        System.out.println("ArrayQueue: time:" +time1+"s");
        System.out.println("LoopQueue: time:" +time2+"s");
    }
}
