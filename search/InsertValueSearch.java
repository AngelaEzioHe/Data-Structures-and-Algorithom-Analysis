package search;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/4/3 15:45
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length-1, 100);
        System.out.println("index=" + index);
        //System.out.println(Arrays.toString(arr));
    }

    //编写差值查找算法

    /**
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找的值
     * @return 如果找到，就返回对应的下标,如果没有，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("查找次数~~~");
        //findVal < arr[0] || findVal > arr[arr.length - 1] 不仅起到优化作用，而且必须要有
        //否则 mid 可能会越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出 mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
