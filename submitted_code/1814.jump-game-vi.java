//
// @lc app=leetcode.cn id=1814 lang=java
//
// [1814] jump-game-vi
//
class Solution {
    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        int end = k;
        int result = nums[0];
        int i = 0; 
        while (i < len) {
            end = Math.min(i + k, len - 1);
            if(i + 1 > end)
                break;
            int max = findMax(nums, i + 1, end);
            result += nums[max];
            i = max;
        }
        return result;
    }
    private static int findMax(int[] copy, int start, int end) {
        int max = start;
        int temp = copy[start];
        while (start <= end) {
            if (copy[start] > 0)
                return start;
            if (copy[start] > temp) {
                max = start;
                temp = copy[start];
            }
            ++start;
        }
        if (start == copy.length)
            return start-1;
        return max;
    }
}
// @lc code=end