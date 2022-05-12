//
// @lc app=leetcode.cn id=34 lang=java
//
// [34] find-first-and-last-position-of-element-in-sorted-array
//
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1])
            return res;
        int l = 0, r = nums.length-1;
        while(l < r){
            int mid = l + r +1 >> 1;
            if(nums[mid] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        if(nums[l] == target)
            res[1] = l;
        l = 0; r = nums.length-1;
        while(l < r){
            int mid = l + r >> 1;
            if(nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        if(nums[l] == target)
            res[0] = l;
        return res;
    }
}
// @lc code=end