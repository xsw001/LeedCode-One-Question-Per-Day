//
// @lc app=leetcode.cn id=485 lang=java
//
// [485] max-consecutive-ones
//
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int l = nums.length,res = 0,temp = 0;
        for(int i=0;i<l;++i){
            while(i<l && nums[i]==1){
                ++temp;
                ++i;
            }
            res = Math.max(res,temp);
            temp = 0;
        }
        return res;
    }
}
// @lc code=end