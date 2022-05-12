//
// @lc app=leetcode.cn id=100331 lang=java
//
// [100331] que-shi-de-shu-zi-lcof
//
class Solution {
    public int missingNumber(int[] nums) {
        if(nums[0] == 1)
            return 0;
        int l = 0, r = nums.length-1;
        while(l < r){
            int m = (l+r+1)/2;
            if(nums[m] > m)
                r = m - 1;
            else
                l = m;
        }
        return l+1;
    }
}
// @lc code=end