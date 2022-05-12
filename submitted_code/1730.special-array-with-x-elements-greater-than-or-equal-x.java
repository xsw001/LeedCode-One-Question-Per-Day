//
// @lc app=leetcode.cn id=1730 lang=java
//
// [1730] special-array-with-x-elements-greater-than-or-equal-x
//
class Solution {
    public int specialArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length+1; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if(nums[j]>=i){
                    ++count;
                }
            }                
            if(count == i)
                return i;
        }
        return -1;
    }
}
// @lc code=end