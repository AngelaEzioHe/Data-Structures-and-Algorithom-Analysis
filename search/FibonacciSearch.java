package search;

import java.util.Arrays;

/**
 * @Author: EzioHe
 * @Date: 2023/4/3 17:28
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index="+fibSearch(arr,8));
    }

    //需要先获取一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 0;
        f[1] = 0;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //非递归的方式

    /**
     * @param a   数组
     * @param key 我们需要查找的关键码（值）
     * @return 返回对应的下标，如果没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放 mid 值
        int[] f = fib(); //获取斐波那契数列

        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        //因为 k[k] 值可能大于数组长度，我们需要使用 Arrays 类构造一个新的数组，并指向 a[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需求使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //向数组左边查找
                high = mid - 1;
                /*
                    说明：
                    1.全部元素=前面的元素+后面的元素
                    2.f[k]=f[k-1]+f[k-2]
                    因为前面有 f[k-1]个元素，所以我们可以继续拆分 f[k-1]=f[k-2]+f[k-3]
                    即在f[k-1]的前面继续查找 k--
                    即下次循环 mid=f[k-1-1]-1
                 */
                k--;
            } else if (key > temp[mid]) { //向数组右边查找
                low = mid + 1;
                /*
                    说明：
                    1.全部元素=前面的元素+后面的元素
                    2.f[k]=f[k-1]+f[k-2]
                    因为后面有 f[k-2]个元素，所以我们可以继续拆分 f[k-1]=f[k-3]+f[k-4]
                    即在f[k-2]的前面继续查找 k-=2
                    即下次循环 mid=f[k-1-2]-1
                 */
                k -= 2;
            }else {
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
