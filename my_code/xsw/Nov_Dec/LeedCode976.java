package xsw.Nov_Dec;/*
976. 三角形的最大周长
给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

如果不能形成任何面积不为零的三角形，返回 0。



示例 1：

输入：[2,1,2]
输出：5
示例 2：

输入：[1,2,1]
输出：0
示例 3：

输入：[3,2,3,4]
输出：10
示例 4：

输入：[3,6,2,3]
输出：8
*/

import java.util.Arrays;

public class LeedCode976 {
    public static int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        int i = len - 1;
        int a = 0;
        while (i > 1) {
            if (A[i] < A[i - 1] + A[i - 2]) {
                a = A[i];
                break;
            }
            --i;
        }
        if (a == 0)
            return 0;
        int b = A[--i];
        --i;
        while (i >= 0) {
            int c = A[i];
            if (isTriangle(a, b, c)) {
                return a + b + c;
            }
            --i;
        }
        return 0;
    }

    public static boolean isTriangle(int a, int b, int c) {
        return a < b + c && a > Math.abs(b - c);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1,3,5,8,12};
        int perimeter = largestPerimeter(nums);
        System.out.println(perimeter);
    }

    //我还是纯啊
    /*
    假设三角形的边长满足 a≤b≤c，那么这三条边组成面积不为零的三角形的充分必要条件为 a+b>c
    */
    public static int largestPerimeter2(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
