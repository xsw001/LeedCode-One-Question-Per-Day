//
// @lc app=leetcode.cn id=169 lang=c
//
// [169] majority-element
//
int majorityElement(int* nums, int numsSize){
    int key=nums[0];
    int number=0;
    for(int i=0;i<numsSize;i++){
        if(nums[i] == key)
            number++;
        else
            number--;
        if(number <= 0)
            key = nums[i+1];
    }
    return key;
}
// @lc code=end