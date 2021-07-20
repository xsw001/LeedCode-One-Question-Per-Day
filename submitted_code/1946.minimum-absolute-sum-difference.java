//
// @lc app=leetcode.cn id=1946 lang=java
//
// [1946] minimum-absolute-sum-difference
//
class Solution {
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] copy = Arrays.copyOf(nums1, n);
        Arrays.sort(copy);
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i];
            int b = nums2[i];
            if (a == b) continue;
            sum += Math.abs(a - b);
            int l = nearB(copy, b);
            int abs = Math.abs(copy[l] - b);
            if (copy[l] != b && l > 0)
                abs = Math.min(abs, Math.abs(copy[l - 1] - b));
            if (abs < Math.abs(a - b))
                max = Math.max(max, Math.abs(a - b) - abs);
        }
        return (int) ((sum - max) % 1000000007);
    }

    private static int nearB(int[] nums1, int b) {
        int l = 0, r = nums1.length-1;
        while(l < r){
            int mid = (l+r)/2;
            if(nums1[mid] >= b)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
// @lc code=end