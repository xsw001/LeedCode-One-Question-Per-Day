//
// @lc app=leetcode.cn id=1000038 lang=java
//
// [1000038] find-majority-element-lcci
//
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int count = 0;
        int target = nums[0];

        for(int i = 0; i < nums.length; i++) {
            if (count == 0) {
                target = nums[i];
                count++;
                continue;
            }
            if (nums[i] == target) {
                count++;
            } else {
                count--;
            }

        }

        if (count > 0) {
            count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                return target;
            }
        }
        return -1;
    }
}
// @lc code=end