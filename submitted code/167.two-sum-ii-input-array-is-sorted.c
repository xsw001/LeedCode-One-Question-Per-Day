//
// @lc app=leetcode.cn id=167 lang=c
//
// [167] two-sum-ii-input-array-is-sorted
//
int* twoSum(int* numbers, int numbersSize, int target, int* returnSize){
    int start = 0;
    int end = numbersSize - 1;
    

    *returnSize = 2;
    int* ret = (int*)malloc(sizeof(int) * (*returnSize));

    while (start < end) {
        int tmp = numbers[start] + numbers[end];
        if (tmp < target) {
            start++;
        } else if (tmp > target) {
            end--;
        } else {
            break;
        }
    }

    if (start < end) {
        ret[0] = start + 1;
        ret[1] = end + 1;
        return ret;
    }
    
    return NULL;
}
// @lc code=end