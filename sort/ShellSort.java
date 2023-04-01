package sort;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/3/31 23:09
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(arr));
        shellSort2(arr);
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //第一轮排序将10个数据分成5组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共5组，每组有2个元素），步长5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));
        }
    }

    //优化 交换法->移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动（而不是交换）
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到了插入的位置
                    arr[j]=temp;
                }
            }
        }
        System.out.println("排序后："+Arrays.toString(arr));
    }
}
