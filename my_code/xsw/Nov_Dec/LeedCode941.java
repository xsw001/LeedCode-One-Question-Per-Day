package xsw.Nov_Dec;/*
941. 有效的山脉数组
给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

A.length >= 3
在 0 < i < A.length - 1 条件下，存在 i 使得：
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
*/

public class LeedCode941 {

    public static boolean validMountainArray(int[] A) {
        int len = A.length-1;
        int begin = 0;
        int last = len;
        while(begin < len-1 && A[begin] < A[begin + 1])
            ++begin;
        while(last > 1 && A[last] < A[last-1])
            --last;
        return last == begin;
    }

    public static void main(String[] args) {
        int[] arr = {2,0};
        System.out.println(validMountainArray(arr));
    }
}
