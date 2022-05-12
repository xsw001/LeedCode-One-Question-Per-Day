//
// @lc app=leetcode.cn id=4 lang=java
//
// [4] median-of-two-sorted-arrays
//
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0, k = 0;
        int mid = (m + n) / 2;
        int a = 0, b = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                a = b;
                b = nums1[i++];
            } else {
                a = b;
                b = nums2[j++];
            }
            if (k == mid)
                return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
            ++k;
        }
        while (i < m) {
            a = b;
            b = nums1[i++];
            if (k == mid)
                return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
            ++k;
        }
        while (j < n) {
            a = b;
            b = nums2[j++];
            if (k == mid)
                return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
            ++k;
        }
        return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
    }
}
// @lc code=end