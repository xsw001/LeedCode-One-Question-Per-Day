//
// @lc app=leetcode.cn id=494 lang=java
//
// [494] target-sum
//
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int diff = sum - target;    // 根据推导，diff必须是2的倍数，且 大于0
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }

        int neg = diff / 2; // 根据推导，要想数组“计算和”为target，数组中相应的负数和
        int length = nums.length;
        int[][] dp = new int[length + 1][neg + 1];  // dp[i][j]表示：前i个数字中，组成和为j的可能性，有 dp[i][j]种
        dp[0][0] = 1;   // 初始化有一种结果
        for (int i = 1; i <= length; i++) {
            int curNum = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                /*
                    若 j < curNum，则 dp[i][j] 的个数为 dp[i - 1][j]的个数(不用当前数字)
                    若 j >= curNum，则 dp[i][j] 的个数为 dp[i - 1][j]的个数(不用当前数字) 和 dp[i - 1][j - num]的个数(用当前数字) 的和
                */
                dp[i][j] = dp[i - 1][j];
                if (j >= curNum) {
                    dp[i][j] += dp[i - 1][j - curNum];
                }
            }
        }

        return dp[length][neg];
    }
}
// @lc code=end