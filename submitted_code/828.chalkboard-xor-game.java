//
// @lc app=leetcode.cn id=828 lang=java
//
// [828] chalkboard-xor-game
//
class Solution {
    public boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }
}
// @lc code=end