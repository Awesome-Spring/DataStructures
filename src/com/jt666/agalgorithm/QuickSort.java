package com.jt666.agalgorithm;

import java.util.Arrays;
import java.util.Random;


public class QuickSort {
    public static void sort(int[] arr, int start, int end) {

        //递归终止条件
        if (start>=end) {
            return;
        }
        //获得基准值的坐标,以该位置将数组分成两部分（左边都比它小，右边都比它大）
        int pivotIndex = partition1(arr, start, end);
        //递归调用左边，直至不可拆分为止
        sort(arr, start, pivotIndex - 1);
        //递归调用右边边，直至不可拆分为止
        sort(arr, pivotIndex + 1, end);
    }
    /**
     *左右指针法
     * 用伪代码描述如下：
     * （1）left = start; high = end; 选取a[left]作为关键字记录为 pivot 。
     * （2）left--，由后向前找比它小的数
     * （3）right++，由前向后找比它大的数
     * （4）交换第（2）、（3）步找到的数
     * （5）重复（2）、（3），一直往后找，直到left和right相遇，这时将pivot和a[left]交换位置。
     */
    private static int partition(int[] arr, int start, int end) {
        //参数检查
        if (start > arr.length || start < 0 || end > arr.length || end < 0) {
            throw new IllegalArgumentException("The argument is Wrong");
        }
        int pivot = arr[start];//初始化基准值 默认为数组第一个（最好随机选择一个数组元素，将它和第一个元素交换作为基准，防止最坏情况）
        int left = start;//左指针
        int right = end;//右指针

        while (left!=right) { //终止条件，左右指针重合，此时该位置为基准值所在坐标

            while (left<right && arr[right]>=pivot) {//从右往左循环，右边元素大于基准，继续移动（右指针向左）
                right--;
            }
            while (left<right && arr[left]<=pivot) { //从左往右循环，左边元素小于基准，继续移动（左指针向右）
                left++;
            }

            //一轮结束，交换左右指针位置元素
            if(left<right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //循环结束 左右指针重合  基准值和指针位置交换，此时基准位置左边都小于基准值右边都大于，最后返回基准值位置索引
        arr[start] = arr[left];
        arr[left] = pivot;
        return left;
    }

    //单边循环法
    private static int partition1(int[] arr, int start, int end) {
        //参数检查
        if (start > arr.length || start < 0 || end > arr.length || end < 0) {
            throw new IllegalArgumentException("The argument is Wrong");
        }
        int pivot = arr[start];//初始化基准值 默认为数组第一个（最好随机选择一个数组元素，将它和第一个元素交换作为基准，防止最坏情况）
        int mark = start;//小于基准的边界
        //从第二个元素开始遍历整个数组
        for (int i = start+1; i <=end ; i++) {
            //如果小于基准元素，则先将边界（mark++）移动，然后交换mark和该元素位置
            if (arr[i]<= pivot) {
                mark++;
                int temp =arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        //整个遍历结束，将基准元素和mark位置互换，整个数组被分成两部分,最后返回基准元素的索引
        arr[start] = arr[mark];
        arr[mark] = pivot;

       return mark;
    }

    //测试
    public static void main(String[] args) {
        int [] arr = new int[40];
        for (int i = 0; i <40; i++) {
            arr[i] = new Random().nextInt(100);
        }
        System.out.println("排序之前"+ Arrays.toString(arr));
        sort(arr,0,arr.length-1);
        System.out.println("排序之后"+ Arrays.toString(arr));
    }
}
