//
// @lc app=leetcode.cn id=35 lang=java
//
// [35] search-insert-position
//
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while(l < r){
            int mid = l+r >> 1;
            if(nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
// @lc code=end