package sort;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/4/2 17:26
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //得到数组中最大数的位数
        int max = arr[0]; //假设第一个数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();
        /*
            说明
            1.二维数组包含10个一维数组
            2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr.length
            3.明确，基数排序是使用空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放多少个数据，定义一个 一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //取出每个元素的对应位进行排序处理，第一次是个位，第二次是十位，第三次是百位...
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的各位进行排序
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第 i+1 轮后，需要将每个 bucketElementCounts[k] 初始化（归零）！！！
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i+1) + "轮，对个位的排序处理" + Arrays.toString(arr));
        }
    }
}
