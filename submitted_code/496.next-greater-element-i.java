//
// @lc app=leetcode.cn id=496 lang=java
//
// [496] next-greater-element-i
//
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            boolean flag = false;
            int j = 0;
            for (; j < nums2.length; j++) {
                if (nums2[j] == nums1[i])
                    flag = true;
                if (flag && nums2[j] > nums1[i]) {
                    nums1[i] = nums2[j];
                    break;
                }
            }
            if (j == nums2.length)
                nums1[i] = -1;
        }
        return nums1;
    }
}
// @lc code=end