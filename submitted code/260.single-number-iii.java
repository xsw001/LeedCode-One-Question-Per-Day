//
// @lc app=leetcode.cn id=260 lang=java
//
// [260] single-number-iii
//
class Solution {
    public int[] singleNumber(int[] nums) {
        if(nums.length == 2)
            return nums;
        int num1 = 0, num2 = 0;
        int xor = 0;
        for(int num : nums)
            xor ^= num;
        
        int bit_1 = 1;
        while((xor & 1) == 0) {
            xor >>= 1;
            bit_1 <<= 1;
        }

        for(int num : nums) {
            if((num & bit_1) == 0)
                num1 ^= num;
            else
                num2 ^= num;
        }
        
        return new int[]{num1, num2};
    }
}
// @lc code=end