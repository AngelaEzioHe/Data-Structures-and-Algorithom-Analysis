package sort;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/3/30 22:41
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //假定的最小值不是最小的
                    min = arr[j]; //重置min
                    minIndex = j; //重置minIndex
                }
            }

            //将最小值放在arr[0],即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) + "轮后：");
            System.out.println(Arrays.toString(arr));
        }
    }
}
