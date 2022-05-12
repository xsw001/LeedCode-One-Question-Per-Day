//
// @lc app=leetcode.cn id=561 lang=java
//
// [561] array-partition-i
//
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int i = 0,res = 0;
        while(i < nums.length){
            res += nums[i];
            i += 2;
        }
        return res;
    }
}
// @lc code=end