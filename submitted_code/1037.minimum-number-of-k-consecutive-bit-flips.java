//
// @lc app=leetcode.cn id=1037 lang=java
//
// [1037] minimum-number-of-k-consecutive-bit-flips
//
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if (A[i] == revCnt){
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                ++diff[i];
                --diff[i + K];
            }
        }
        return ans;
    }
}
// @lc code=end