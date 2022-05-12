package xsw.Nov_Dec;/*

922. 按奇偶排序数组 II
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。



示例：

输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
*/

import java.util.Arrays;

public class LeedCode922 {
    public static int[] sortArrayByParityII(int[] A) {
        int[] arr = new int[A.length];
        int i = 0, j = 1;
        for (int a : A) {
            if (a % 2 == 0) {
                arr[i] = a;
                i += 2;
            } else {
                arr[j] = a;
                j += 2;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 5, 7, 1, 2, 3, 4, 5, 6};
        int[] sort = sortArrayByParityII1(arr);
        System.out.println(Arrays.toString(sort));
    }

    public static int[] sortArrayByParityII1(int[] A) {
        int i = 0, j = 1;
        while (i < A.length && j < A.length) {
            if (A[i] % 2 == 0)
                i += 2;
            if (A[j] % 2 == 1)
                j += 2;
            if(i < A.length && j < A.length) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        return A;
    }
}
