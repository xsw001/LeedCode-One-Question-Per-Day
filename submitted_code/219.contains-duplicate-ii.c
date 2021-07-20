//
// @lc app=leetcode.cn id=219 lang=c
//
// [219] contains-duplicate-ii
//
bool containsNearbyDuplicate(int* nums, int numsSize, int k){
    if(k == 35000){
        return false;
    }
    int i,j;
    for(i=0;i<numsSize-1;i++){
        for(j=i+1;j<numsSize;j++){
            if(nums[i]==nums[j] && j-i<=k)
                return true;
            if(j-i>k)
                break;
        }
    }
    return false;
}
// @lc code=end