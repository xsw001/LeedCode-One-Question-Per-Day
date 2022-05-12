//
// @lc app=leetcode.cn id=330 lang=java
//
// [330] patching-array
//
class Solution {
    public int minPatches(int[] nums, int n) {
        long curr_range  = 0;
        int m = nums.length;
        int res = 0;
        int pos = 0;
        for(long i = 1 ; i <= n;){
            if( pos >= m || i < nums[pos] ){
                res++;
                curr_range += i;
            }else{
                curr_range += nums[pos];
                pos++;
            }
            i = curr_range + 1;
        }

        return res;
    
    }
}
// @lc code=end