//
// @lc app=leetcode.cn id=1936 lang=java
//
// [1936] maximize-number-of-nice-divisors
//
class Solution {
    public int maxNiceDivisors(int n) {
        if (n < 5) {
            return n;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) (myPow(3, quotient));
        } else if (remainder == 1) {
            return (int) ((myPow(3, quotient - 1) * 4) % 1000000007);
        } else {
            return (int) ((myPow(3, quotient) * 2) % 1000000007);
        }
    }
    public long myPow(int x, int n) {
        long b = n;
        long base = x;
        long res = 1L;
        while(b != 0) {
            if((b & 1) == 1) 
                res = (res * base) % 1000000007;
            base = (base * base) % 1000000007;
            b >>>= 1;
        }
        return res;
    }
}
// @lc code=end