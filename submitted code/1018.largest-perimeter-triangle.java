//
// @lc app=leetcode.cn id=1018 lang=java
//
// [1018] largest-perimeter-triangle
//
class Solution {
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
}
// @lc code=end