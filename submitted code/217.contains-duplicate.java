//
// @lc app=leetcode.cn id=217 lang=java
//
// [217] contains-duplicate
//
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            return false;
        }else{
            int temp;
            for(int i = 1;i < nums.length;i++){
                if(nums[i] == nums[i - 1]){
                    return true;
                }else if(nums[i] < nums[i - 1]){
                   for(int j = i - 2;j >= 0; j--){
                       if(nums[i] == nums[j]){
                           return true;
                       }
                   }
                   temp = nums[i];
                   nums[i] = nums[i - 1];
                   nums[i - 1] = temp;
                }
            }
            return false;
        }
    }
}
// @lc code=end