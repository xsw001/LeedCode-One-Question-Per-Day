//
// @lc app=leetcode.cn id=645 lang=java
//
// [645] set-mismatch
//
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] count = new int[n+1];
        int[] ans = new int[2];
        for(int i=0;i<n;++i){
            ++count[nums[i]];
            if(count[nums[i]] == 2)
                ans[0] = nums[i];
        }
        for(int i=1;i<=n;++i){
            if(count[i] == 0)
                ans[1] = i;
        }
        return ans;
    }
}
// @lc code=end