//
// @lc app=leetcode.cn id=1984 lang=java
//
// [1984] maximum-distance-between-a-pair-of-values
//
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (nums1[l1 - 1] > nums2[0])
            return 0;
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int k = nums1[i];
            int l = i, r = l2 - 1;
            while (l < r) {  // 第一个小于 k 的值
                int mid = (l + r) / 2;
                if (nums2[mid] >= k)
                    l = mid + 1;
                else
                    r = mid;
            }
            if(l < l2)
                if (nums2[l] < k)
                    --l;
            ans = Math.max(ans, l - i);
        }
        return ans;
    }
}
// @lc code=end