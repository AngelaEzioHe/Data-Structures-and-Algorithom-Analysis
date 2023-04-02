package sort;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/4/1 23:47
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70,-1,900,4561};
        System.out.println("排序前arr=" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左索引
        int r = right; // 右索引
        //pivot（中轴值）
        int pivot = arr[(left + right) / 2];
        int temp;
        //while循环的目的是让比 pivot值小的放到左边，比pivot值打的放到右边
        while (l < r) {
            //在pivot左边一直找，找到>=pivot的值，才退出
            while ((arr[l] < pivot)) {
                l++;
            }
            //在pivot右边一直找，找到<=pivot的值，才退出
            while (arr[r] > pivot) {
                r--;
            }
            //如果l >= r ，说明 pivot 的左右两边的值，已经按照左边全部是 <= pivot 值，右边全部是 >= pivot 值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现 arr[l] == pivot , r--(前移）
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现 arr[r] == pivot , l--(后移）
            if (arr[r] == pivot) {
                l++;
            }
        }
        //如果 l == r ,必须 l++,r--,否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
