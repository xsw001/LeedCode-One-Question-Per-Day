//
// @lc app=leetcode.cn id=368 lang=java
//
// [368] largest-divisible-subset
//
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        Arrays.sort(nums);
        int max = nums[0], maxSize = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    max = nums[i];
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = len-1; i >= 0; i--) {
            if (dp[i] == maxSize && max % nums[i] == 0) {
                res.add(nums[i]);
                max = nums[i];
                --maxSize;
            }
            if (0 == maxSize)
                break;
        }
        return res;
    }
}
// @lc code=end