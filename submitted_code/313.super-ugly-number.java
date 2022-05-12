//
// @lc app=leetcode.cn id=313 lang=java
//
// [313] super-ugly-number
//
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < m; k++) {
                int temp = dp[pointers[k]] * primes[k];

                if (temp < min) {
                    min = temp;
                }

            }
            dp[i] = min;
            for (int j = 0; j < pointers.length; j++) {
                if (dp[i] == dp[pointers[j]] * primes[j]) {
                    pointers[j]++; // 所有的都要加，不能break;
                }
            }
        }
        return dp[n];
    }
}
// @lc code=end