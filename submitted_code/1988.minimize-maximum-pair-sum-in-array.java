//
// @lc app=leetcode.cn id=1988 lang=java
//
// [1988] minimize-maximum-pair-sum-in-array
//
class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int answer = 0;
        int l = 0, r = nums.length-1;
        while (l < r){
            answer = Math.max(answer, nums[l++]+nums[r--]);
        }
        return answer;
    }
}
// @lc code=end