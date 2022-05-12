//
// @lc app=leetcode.cn id=1580 lang=java
//
// [1580] shuffle-the-array
//
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] arr = new int[2*n];
        int i = 0, j = n,k = 0;
        while(j < n*2){
            arr[k++] = nums[i++];
            arr[k++] = nums[j++];
        }
        return arr;
    }
}
// @lc code=end