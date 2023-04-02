package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: EzioHe
 * @Date: 2023/4/2 21:45
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000,1000,1000,1000, 1234};
        List resIndexList = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }

    //二分查找算法

    /**
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        //当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int minVal = arr[mid];
        if (findVal > minVal) { //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < minVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            /*
                思考题：
                在数组中，有多个相同的数值时，如何将所欲的数值都查找到
                1.在找到 mid 值时，不要马上返回
                2.向 mid 索引值的左边扫描，将所有满足要查找元素的下标加入到集合 ArrayList
                3.向 mid 索引值的右边扫描，将所有满足要查找元素的下标加入到集合 ArrayList
                4.将 ArrayList 返回
            */
            ArrayList<Integer> resIndexlist = new ArrayList<Integer>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则就把 temp 放到集合中
                resIndexlist.add(temp);
                temp--;
            }
            resIndexlist.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexlist.add(temp);
                temp++;
            }
            return resIndexlist;
        }
    }
}
