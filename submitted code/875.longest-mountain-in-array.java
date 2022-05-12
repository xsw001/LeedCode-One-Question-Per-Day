//
// @lc app=leetcode.cn id=875 lang=java
//
// [875] longest-mountain-in-array
//
class Solution {
    public static int longestMountain(int[] A) {
        if (A == null || A.length <= 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < A.length - 1; i++) {
            int r = 0, l = 0;
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                l = i - 1;
                r = i + 1;
                while (l > 0 && A[l] > A[l - 1]) {
                    --l;
                }
                while (r < A.length-1 && A[r] > A[r + 1]) {
                    ++r;
                }
                
                res = Math.max(res, r - l + 1);
            }

        }
        return res;
    }
}
// @lc code=end