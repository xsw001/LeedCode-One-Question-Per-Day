//
// @lc app=leetcode.cn id=268 lang=c
//
// [268] missing-number
//
int missingNumber(int* nums, int numsSize){
    int ans = numsSize, i;
    for(i = 0; i < numsSize; i++){
        ans ^= i ^ nums[i];
    }
    return ans;
}
// @lc code=end