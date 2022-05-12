//
// @lc app=leetcode.cn id=441 lang=java
//
// [441] arranging-coins
//
class Solution {
    public int arrangeCoins(int n) {
        long l = 1, r = 65535;
        while (l < r) {
            long mid = (l + r) / 2 + 1;
            if ((mid * (mid + 1))/2 >  n) {
                r = mid - 1;
            } else
                l = mid;
        }
        return (int)l;
    }
}
// @lc code=end