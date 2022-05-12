//
// @lc app=leetcode.cn id=673 lang=java
//
// [673] number-of-longest-increasing-subsequence
//
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int max = 0,ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    if(dp[j] + 1 > dp[i]){// 可以接在后边
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; // 原来有几个最长的，再接一个还是几个最长的
                    }else if(dp[j]+1 == dp[i]){// 接在后边，也是最长的，那就加起来
                        cnt[i] += cnt[j];
                    }
                }
            }
            if(dp[i] > max){
                max = dp[i];
                ans = cnt[i];
            }else if(dp[i] == max)
                ans += cnt[i];
        }
        return ans;
    }
}
// @lc code=end