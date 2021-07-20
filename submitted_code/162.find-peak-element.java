//
// @lc app=leetcode.cn id=162 lang=java
//
// [162] find-peak-element
//
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l < r){
            int mid = l + (r-l)/2; //mid 不可能取到最后一个点，最后一个点的条件是 l= r= nums.length-1，此时退出循环
            if(nums[mid] > nums[mid+1])// 所以不必要判断 mid < nums.length-1
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
// @lc code=end