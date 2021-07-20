//
// @lc app=leetcode.cn id=154 lang=java
//
// [154] find-minimum-in-rotated-sorted-array-ii
//
class Solution {
    public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while(r >= 0 && nums[l] == nums[r])
            --r;
        while(l < r){
            int mid = l + (r-l)/2;
            if(nums[mid] <= nums[r])
                r = mid;
            else
                l = mid+1;
        }
        return nums[l];
    }
}
// @lc code=end