//
// @lc app=leetcode.cn id=1421 lang=cpp
//
// [1421] find-numbers-with-even-number-of-digits
//
class Solution {
public:
    int findNumbers(vector<int>& nums) {
        int i = 0;
        int number = 0;
        while(i < nums.size()){
            int tempNum = 0;
            int tmp = nums[i];
            while(tmp!=0){
                ++tempNum;
                tmp = tmp/10;
            }
            if(tempNum%2==0)
                ++number;
            ++i;
        }
        return number;
    }
};
// @lc code=end