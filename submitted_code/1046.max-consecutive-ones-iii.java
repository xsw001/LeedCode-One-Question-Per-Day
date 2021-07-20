//
// @lc app=leetcode.cn id=1046 lang=java
//
// [1046] max-consecutive-ones-iii
//
class Solution {
    public int longestOnes(int[] A, int K) {
        int res = 0;
        int left = 0, right = 0;
        int freq = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                freq++;
            }
            right++;
            while (freq > K) {
                if (A[left] == 0) {
                    freq--;
                }
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
// @lc code=end