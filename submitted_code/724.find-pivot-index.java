//
// @lc app=leetcode.cn id=724 lang=java
//
// [724] find-pivot-index
//
class Solution {
    public int pivotIndex(int[] nums) {
        if(nums.length < 1)
            return -1;
        int num = 0;
        for(int i : nums){
            num += i;
        }
        int left = 0;
        int rigth = num;
        for(int i = 0; i < nums.length; ++i){
            rigth -= nums[i];
            if(rigth == left)
                return i;
            left += nums[i];
            num = rigth;
        }
        return -1;
    }
}
// @lc code=end