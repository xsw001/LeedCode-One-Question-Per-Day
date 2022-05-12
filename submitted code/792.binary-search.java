//
// @lc app=leetcode.cn id=792 lang=java
//
// [792] binary-search
//
class Solution {
    public int search(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while(l < r){
            int mid= (l+r)/2 +1 ;
            if(nums[mid] <= target)
                l = mid;
            else
                r = mid -1;
        }
        return nums[l] == target ? l : -1;
    }
}
// @lc code=end