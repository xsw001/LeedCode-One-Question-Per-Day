//
// @lc app=leetcode.cn id=594 lang=java
//
// [594] longest-harmonious-subsequence
//
class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int l = 0, r = 0;

        while (r < nums.length) {
            while (nums[r] > nums[l] + 1)
                ++l;
            if (nums[r] == nums[l]+1) {
                ans = Math.max(ans, r - l + 1);
            }
            ++r;
        }
        return ans;
    }
}
// @lc code=end