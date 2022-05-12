//
// @lc app=leetcode.cn id=80 lang=java
//
// [80] remove-duplicates-from-sorted-array-ii
//
class Solution {
    public int removeDuplicates(int[] nums) {
                int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
// @lc code=end