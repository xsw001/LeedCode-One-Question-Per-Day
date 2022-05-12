//
// @lc app=leetcode.cn id=1935 lang=java
//
// [1935] minimum-number-of-operations-to-reinitialize-a-permutation
//
class Solution {
    public int reinitializePermutation(int n) {
        if (n == 2) {
            return 1;
        }
        int k = 1;
        int pow2 = 2;
        while (pow2 != 1) {
            k++;
            pow2 = pow2 * 2 % (n - 1);
        }
        return k;
    }
}
// @lc code=end