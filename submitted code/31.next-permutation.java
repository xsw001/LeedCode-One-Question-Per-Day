//
// @lc app=leetcode.cn id=31 lang=java
//
// [31] next-permutation
//
class Solution {
public static void nextPermutation(int[] nums) {
        int min = 0;
        int max = 0;
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                min = i - 1;
                break;
            }
        }
        if (i > 0) {
            for (int j = nums.length - 1; j > min; j--) {
                if (nums[j] > nums[min]) {
                    max = j;
                    break;
                }
            }
            swap(nums, min, max);
            reverse(nums, min + 1);
        }else
            reverse(nums,min);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int nums[], int start) {
        int end = nums.length-1;
        while (end > start) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
// @lc code=end