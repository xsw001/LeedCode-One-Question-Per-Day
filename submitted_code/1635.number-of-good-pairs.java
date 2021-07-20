//
// @lc app=leetcode.cn id=1635 lang=java
//
// [1635] number-of-good-pairs
//
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        int[] xsw = new int[101];
        for(int i=0;i<nums.length;++i){
            xsw[nums[i]]++;
        }
        for(int x : xsw){
            if(x > 1){
                count += x*(x-1)/2;
            }
        }
        return count;
    }
}
// @lc code=end