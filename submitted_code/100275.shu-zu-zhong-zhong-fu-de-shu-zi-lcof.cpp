//
// @lc app=leetcode.cn id=100275 lang=cpp
//
// [100275] shu-zu-zhong-zhong-fu-de-shu-zi-lcof
//
class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
       for(int i = 0; i < nums.size(); i++) {
            if(nums[i] != i) {
                if( nums[i] != nums[nums[i]]) {
                    int temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }else{
                    return nums[i];
                }
            } 
        }
        return -1;
    }
};
// @lc code=end