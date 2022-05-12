//
// @lc app=leetcode.cn id=100329 lang=java
//
// [100329] zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
//
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0)
            return 0;
        int l = 0, r = nums.length -1;
        while(l < r){
            int mid = (l+r)/2;
            if(nums[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        if(nums[l] != target)
            return 0;
        int a = l;
        l = 0;
        r = nums.length-1;
        while(l < r){
            int mid = (l+r+1)/2;
            if(nums[mid] > target)
                r = mid - 1;
            else
                l = mid;
        }
        return l - a + 1;
    }
}
// @lc code=end