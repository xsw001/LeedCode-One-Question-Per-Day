//
// @lc app=leetcode.cn id=748 lang=java
//
// [748] largest-number-at-least-twice-of-others
//
class Solution {
    public int dominantIndex(int[] nums) {
        int l = nums.length;
        int a = -1, b = -1;
        int ans = -1;
        for(int i=0;i<l;++i){
            if(nums[i] >= a){
                ans = i;
                b = a;
                a = nums[i];
            }else if(nums[i] >= b)
                b = nums[i];
        }
        return a >= b*2 ? ans : -1;
    }
}
// @lc code=end