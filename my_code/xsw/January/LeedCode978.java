package xsw.January;
/*
978. 最长湍流子数组
当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

返回 A 的最大湍流子数组的长度。



示例 1：

输入：[9,4,2,10,7,8,8,1,9]
      f f t  f t f f t
      1 1 0 1  0 1 1 0 1
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])
示例 2：

输入：[4,8,12,16]
      t t t  t
输出：2
示例 3：

输入：[100]
输出：1


提示：

1 <= A.length <= 40000
0 <= A[i] <= 10^9
 */

import java.util.ArrayList;

public class LeedCode978 {

    public static int maxTurbulenceSize(int[] arr) {
        int max = 1;
        int l = arr.length;
        if (l == 1)
            return 1;
        int[] flag = new int[l - 1];
        int num = 0;
        for (int i = 0; i < l - 1; i++) {
            if (arr[i] < arr[i + 1])
                flag[i] = 0;
            else if (arr[i] > arr[i + 1])
                flag[i] = 1;
            else {
                flag[i] = 2;
                ++num;
            }
        }
        if (num == l - 1)
            return 1;
        for (int i = 0; i < l; i++) {
            int temp = 1;
            while (i < l - 2 && flag[i] != flag[i + 1] && flag[i] != 2 && flag[i + 1] != 2) {
                ++i;
                ++temp;
            }
            max = Math.max(max, temp);
        }
        return max + 1;
    }

    public static void main(String[] args) {
        int[] arr = {9, 9, 4, 2, 10, 7, 8, 8, 1, 9};
        System.out.println(maxTurbulenceSize1(arr));
    }

    public static int maxTurbulenceSize1(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }

}
//9>4>2<10>7<8=8>1<9