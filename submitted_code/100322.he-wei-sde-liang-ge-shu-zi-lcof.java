//
// @lc app=leetcode.cn id=100322 lang=java
//
// [100322] he-wei-sde-liang-ge-shu-zi-lcof
//
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(l < r){
            if(nums[r] + nums[l] == target)
                return new int[]{nums[r],nums[l]};
            if(nums[r] + nums[l] > target)
                --r;
            else
                ++l;
        }
        return new int[]{};
    }
}
// @lc code=end