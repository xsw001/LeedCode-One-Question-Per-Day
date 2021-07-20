//
// @lc app=leetcode.cn id=1482 lang=java
//
// [1482] how-many-numbers-are-smaller-than-the-current-number
//
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;++i){
            int temp = 0;
            for(int j=0;j<nums.length;++j){
                if(i!=j && nums[j] < nums[i])
                    ++temp;
            }
            res[i]=temp;
        }
        return res;
    }
}
// @lc code=end