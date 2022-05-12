//
// @lc app=leetcode.cn id=1965 lang=java
//
// [1965] sum-of-digits-in-base-k
//
class Solution {
    public int sumBase(int n, int k) {
        int res = 0;
        while (n != 0){
            res += n%k;
            n /= k;
        }
        return res;
    }
}
// @lc code=end