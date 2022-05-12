//
// @lc app=leetcode.cn id=1913 lang=cpp
//
// [1913] make-the-xor-of-all-segments-equal-to-zero
//
class Solution {
public:
    static constexpr int N = 1024; // 2^10
    int minChanges(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> group_amount(k);
        vector<unordered_map<int, int>> group_record(k);
        for(int i = 0; i < n; i++) {
            group_amount[i%k]++;
            group_record[i%k][nums[i]]++;
        }
            
        vector<vector<int>> dp(k, vector<int>(N));
        for(int j = 0; j < N; j++)
            dp[0][j] = group_amount[0] - group_record[0][j];
        
        for(int i = 1; i < k; i++) {
            int upper_limit = *min_element(dp[i-1].begin(), dp[i-1].end()) + group_amount[i];
            fill(dp[i].begin(), dp[i].end(), upper_limit);

            for(auto [num, amount] : group_record[i%k])
                for(int j = 0; j < N; j++)
                    dp[i][j^num] = min(dp[i][j^num], dp[i-1][j] + group_amount[i] - amount);
        }
        return dp[k-1][0];
    }
};
// @lc code=end