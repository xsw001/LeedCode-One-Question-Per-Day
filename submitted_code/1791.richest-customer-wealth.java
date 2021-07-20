//
// @lc app=leetcode.cn id=1791 lang=java
//
// [1791] richest-customer-wealth
//
class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for(int[] account : accounts){
            int temp = 0;
            for(int i : account){
                temp += i;
            }
            max = Math.max(max,temp);
        }
        return max;
    }
}
// @lc code=end