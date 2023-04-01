package sort;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/3/31 19:23
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,-1,89};
        insertSort(arr);
    }

    //插入排序
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1; //即arr[1] 前面这个数的下标
            //给 insertVal 找到插入的位置
        /*
            1.insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            3.就需要将 arr[insertIndex] 后移
         */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出 while 循环时，插入位置找到，insertIndex + 1
            arr[insertIndex + 1] = insertVal;
            System.out.println("第" + i + "轮插入后：" + Arrays.toString(arr));
        }
    }
}
