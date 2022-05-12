//
// @lc app=leetcode.cn id=334 lang=java
//
// [334] increasing-triplet-subsequence
//
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= a)
                a = num;
            else if (num <= b)
                b = num;
            else
                return true;
        }
        return false;
    }
}
// @lc code=end