//
// @lc app=leetcode.cn id=1549 lang=java
//
// [1549] longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
//
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int l = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while (right < l) {
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            while(map.lastKey() - map.firstKey() > limit){
                map.put(nums[left],map.get(nums[left])-1);
                if(map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }
                ++left;
            }
            res = Math.max(res, right - left + 1);
            ++right;
        }
        return res;
    }
}
// @lc code=end