//
// @lc app=leetcode.cn id=153 lang=java
//
// [153] find-minimum-in-rotated-sorted-array
//
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        int res = nums[r];
        while(l < r){
            int mid = l + r >> 1;
            if(nums[mid] <= res){
                r = mid;
                res = nums[mid];
            }else
                l = mid+1;
        }
        return res;
    }
}
// @lc code=end