package com.jt666.agalgorithm;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithm {

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        //对原数组进行拷贝
        int[] a = Arrays.copyOf(arr, arr.length);

        //外层控制循环次数（两两交换）
        for (int i = 0; i < a.length - 1; i++) {
            //设计一个标记，若为true表示此次数组有序，无需交换,从而提升性能
            boolean flag = true;
            //从第一个考元素开始比较
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    flag = false;
                }
            }
            if (flag) { //有序，无需交换，
                break;
            }
        }

        return a;
    }

    public static void quickSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }
        int pivot = partition(arr, start, end);//基准值索引

        //递归
        quickSort(arr, start, pivot - 1);
        quickSort(arr, pivot + 1, end);

    }

    //单边循环法
    private static int partition(int[] arr, int start, int end) {
        //参数检查
        if (start > arr.length || start < 0 || end > arr.length || end < 0) {
            throw new IllegalArgumentException("The argument is Wrong");
        }
        int pivot = arr[start];//初始化基准值 默认为数组第一个（最好随机选择一个数组元素，将它和第一个元素交换作为基准，防止最坏情况）
        int mark = start;//小于基准的边界
        //从第二个元素开始遍历整个数组
        for (int i = start + 1; i <= end; i++) {
            //如果小于基准元素，则先将边界（mark++）移动，然后交换mark和该元素位置
            if (arr[i] <= pivot) {
                mark++;

                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        //整个遍历结束，将基准元素和mark位置互换，整个数组被分成两部分,最后返回基准元素的索引
        arr[start] = arr[mark];
        arr[mark] = pivot;

        return mark;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        //参数检查
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is Empty!");
        }
        //对原数组进行拷贝
        int[] a = Arrays.copyOf(arr, arr.length);

        //从第二个位置往后遍历数组，每遍历一个依次与之前的数比较，插入到合适位置
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i]; // 先取出待插入数据保存，因为向后移位过程中会把覆盖掉待插入数
            while (j >= 0 && a[j] > temp) { //
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
        return a;
    }

    public static int[] selectSort(int[] arr) {
        //参数检查
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is Empty!");
        }
        //对原数组进行拷贝
        int[] a = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < a.length - 1; i++) {
            int min = i;//最小值索引
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(a, i, min);
            }
        }
        return a;
    }




    public static int[] shellSort(int[] arr) {
        //对原数组进行拷贝
        int[] a = Arrays.copyOf(arr, arr.length);

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
        return a;
    }

    //交换a[i] 和 a[i]
    private static void swap(int[] a, int i, int j) {
        if (i < 0 || i > a.length || j < 0 || j > a.length) {
            throw new IllegalArgumentException("index is illeagal");
        }
        a[i] = a[i] + a[j];
        a[j] = a[i] - a[j];
        a[i] = a[i] - a[j];
    }

    //测试
    public static void main(String[] args) {
        int optionNumber = 100000;
        int[] arr = new int[optionNumber];
        for (int i = 0; i < optionNumber; i++) {
            arr[i] = new Random().nextInt(Integer.MAX_VALUE);
        }

        double t1 = System.currentTimeMillis();
//        arr = bubbleSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        arr = insertSort(arr);
//        arr = selectSort(arr);
//        arr = shellSort(arr);

        double t2 = System.currentTimeMillis();
        System.out.println("Spend Time:" + (t2 - t1) / 1000 + "s");
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                throw new RuntimeException("测试失败");
            }
        }
        System.out.println("测试成功");

    }
}

