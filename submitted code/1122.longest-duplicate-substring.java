//
// @lc app=leetcode.cn id=1122 lang=java
//
// [1122] longest-duplicate-substring
//
class Solution {
    int a1, a2, mod1, mod2;

    public String longestDupSubstring(String s) {
        Random random = new Random();
        a1 = random.nextInt(26) + 26;
        a2 = random.nextInt(26) + 26;

        mod1 = random.nextInt(Integer.MAX_VALUE - 779039615 + 1) + 779039615;
        mod2 = random.nextInt(Integer.MAX_VALUE - 779039615 + 1) + 779039615;

        int length = s.length();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int l = 0, r = length - 1;
        int len = 0, start = -1;
        while (l <= r) {
            int m = (l + r + 1) / 2;
            int idx = check(arr, m);
            if (idx != -1) {
                l = m + 1;
                start = idx;
                len = m;
            } else
                r = m - 1;
        }
        return start == -1 ? "" : s.substring(start, start + len);
    }

    private int check(int[] arr, int m) {
        int n = arr.length;
        long aL1 = powSlow(m, 1);
        long aL2 = powSlow(m, 2);

        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; i++) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
            if (h1 < 0)
                h1 += mod1;
            if (h2 < 0)
                h2 += mod2;
        }
        HashSet<Long> set = new HashSet<>();
        set.add(h1 * mod2 + h2);
        for (int i = 1; i <= n - m; i++) {
            h1 = (h1 * a1 % mod1 - arr[i - 1] * aL1 % mod1 + arr[i + m - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[i - 1] * aL2 % mod2 + arr[i + m - 1]) % mod2;
            if (h1 < 0)
                h1 += mod1;
            if (h2 < 0)
                h2 += mod2;
            long num = h1 * mod2 + h2;
            if (!set.add(num))
                return i;
        }
        return -1;
    }

    private long pow(int m, int i) {
        int a = i == 1 ? a1 : a2;
        int mod = i == 1 ? mod1 : mod2;
        long ans = 1, contribute = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0)
                    ans += mod;
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0)
                contribute += mod;
            m /= 2;
        }
        return ans;
    }

    private long powSlow(int m, int i) {
        int a = i == 1 ? a1 : a2;
        int mod = i == 1 ? mod1 : mod2;
        long ans = 1;
        while (m > 0) {
            ans *= a;
            ans %= mod;
            --m;
        }
        return ans;
    }
}
// @lc code=end