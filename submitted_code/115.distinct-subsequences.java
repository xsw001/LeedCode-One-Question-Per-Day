//
// @lc app=leetcode.cn id=115 lang=java
//
// [115] distinct-subsequences
//
class Solution {
    public int numDistinct(String s, String t) {
        int tl = t.length();
        int sl = s.length();
        if (tl > sl)
            return 0;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int[][] nums = new int[sl+1][tl+1];
        for (int[] num : nums) {
            num[tl] = 1;
        }
        for (int i = sl - 1; i >= 0; i--) {
            for (int j = tl - 1; j >= 0; j--) {
                if(sArr[i] == tArr[j]){
                    nums[i][j] = nums[i+1][j+1]+nums[i+1][j];
                }else
                    nums[i][j] = nums[i+1][j];
            }
        }
        return nums[0][0];
    }
}
// @lc code=end