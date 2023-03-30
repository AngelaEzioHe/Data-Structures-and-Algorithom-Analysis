package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: EzioHe
 * @Date: 2023/3/30 21:38
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
//        System.out.println("排序前："+Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后："+Arrays.toString(arr));

        //测试一下冒泡排序的速度 o(n^2),给80000个数，测试
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成一个[0,8000000)的数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str=simpleDateFormat.format(date1);
        System.out.println("排序前的时间是="+date1Str);

        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str=simpleDateFormat.format(date2);
        System.out.println("排序后的时间是="+date2Str);

    }

    public static void bubbleSort(int[] arr) {
        //第一趟排序，就是将最大的数排在最后
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false; //表示变量，表示是否进行过交换
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) { //在一趟排序中，一次交换都没有发生
                break;
            } else {
                flag = false; //重置 flag 进行下次判断
            }
        }
    }
}
