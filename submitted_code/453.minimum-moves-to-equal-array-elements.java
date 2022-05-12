//
// @lc app=leetcode.cn id=453 lang=java
//
// [453] minimum-moves-to-equal-array-elements
//
class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length - 1;
        int ans = 0;
        int min = nums[0], max = nums[l];
        while (min < max) {
            ans += max - min;
            max = max - min + nums[l-1];
            min = nums[l];
            --l;
        }
        return ans;
    }
}
// @lc code=end