//
// @lc app=leetcode.cn id=100291 lang=java
//
// [100291] diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
//
class Solution {
    public int[] exchange(int[] nums) {
        int l = 0 , r = nums.length-1;
        while(l < r){
            while(l < r && nums[l] % 2 == 1) ++l;
            while(l < r && nums[r] % 2 == 0) --r;
            int t = nums[l];
            nums[l++] = nums[r];
            nums[r--] = t;
        }
        return nums;
    }
}
// @lc code=end