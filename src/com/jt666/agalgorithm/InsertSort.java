package com.jt666.agalgorithm;

import java.util.Arrays;
import java.util.Random;

public class InsertSort {

    /**
     * 移位法
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i]; // 先取出待插入数据保存，因为向后移位过程中会把覆盖掉待插入数
            while (j >= 0 && arr[j] >temp) { // 如果待是比待插入数据大，就后移
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp; // 找到比待插入数据小的位置，将待插入数据插入
        }
    }

    //测试
    public static void main(String[] args) {
        int [] arr = new int[40];
        for (int i = 0; i <40; i++) {
            arr[i] = new Random().nextInt(100);
        }
        System.out.println("排序之前"+ Arrays.toString(arr));
        sort(arr);
        System.out.println("排序之后"+ Arrays.toString(arr));
    }
}
