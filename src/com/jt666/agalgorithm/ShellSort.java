package com.jt666.agalgorithm;

import java.util.Arrays;
import java.util.Random;

public class ShellSort {
    /**
     * 希尔排序是对插入排序的改进，交换的是不相邻的元素以对数组的局部进行排序，并最终用插入排序将局部有序的数组排序。
     * 希尔排序先使数组中任意间隔为h的元素都是有序的，这样的数组被称为h有序数组（一个h有序数组即一个由h个有序子数组组成的数组），在进行排序时，如果h很大，就能将元素移动到很远的地方，为实现更小的h有序创造方便。
     * 希尔排序又称“缩小增量排序”，对于每个h，用插入排序将h个子数组独立地排序，只需要在插入排序的代码中将移动元素的距离由1改为h即可，这样希尔排序的实现就转化为一个类似于插入排序但使用不同增量的过程。
     * 希尔排序的执行时间依赖于增量序列，希尔增量时间复杂度为O(N^2)，Hibbard增量的希尔排序时间复杂度为O(N的1.5次方），下界为N*log2N，不稳定的排序算法。
     */
    public static void sort(int[] a) {
        int N = a.length;

        for (int h = N / 2; h > 0; h /= 2) {//希尔增量
            for (int i = h; i < N; i++) {
                //将a[i]插入到a[i-h],a[i-2h],a[i-3h]...中
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                    int temp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = temp;
                }
            }
        }
    }

    //测试
    public static void main(String[] args) {
        int[] arr = new int[40];
        for (int i = 0; i < 40; i++) {
            arr[i] = new Random().nextInt(100);
        }
        System.out.println("排序之前" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序之后" + Arrays.toString(arr));

    }

}
