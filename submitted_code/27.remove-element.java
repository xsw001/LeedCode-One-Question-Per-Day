//
// @lc app=leetcode.cn id=27 lang=java
//
// [27] remove-element
//
class Solution {
    public int removeElement(int[] nums, int val) {
        int l = 0, r = nums.length-1;
        while(l <= r){
            while(r >= 0 && nums[r] == val){
                --r;
            }
            if(r < 0 || r < l)
                break;
            if(nums[l] == val){
                nums[l] = nums[r--];
            }
            ++l;
        }
        return l;
    }
}
// @lc code=end