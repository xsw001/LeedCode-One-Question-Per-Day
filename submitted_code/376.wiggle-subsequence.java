//
// @lc app=leetcode.cn id=376 lang=java
//
// [376] wiggle-subsequence
//
class Solution {
public int wiggleMaxLength(int[] nums) {
        int l = nums.length;
        if (nums.length < 2) {
            return l;
        }
        int result = 1;
        int ud = 0;
        for (int i = 1; i < l; i++) {
            if (nums[i] < nums[i - 1] && ud != -1) {
                ud = -1;
                result++;
            }
            if (nums[i] > nums[i - 1] && ud != 1) {
                ud = 1;
                result++;
            }
        }
        return result;
}

}
// @lc code=end