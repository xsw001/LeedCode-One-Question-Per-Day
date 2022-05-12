//
// @lc app=leetcode.cn id=1056 lang=java
//
// [1056] capacity-to-ship-packages-within-d-days
//
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int l = 0, r = 0;
        for (int weight : weights) {
            if (weight > l)
                l = weight;
            r += weight;
        }
        while (l <= r) {
            int mid = (l + r) / 2;
            int cur = 0, count = 1;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    ++count;
                    cur = 0;
                }
                cur += weight;
            }
            if (count > D)
                l = mid+1 ;
            else
                r = mid- 1;
        }
        return l;
    }
}
// @lc code=end