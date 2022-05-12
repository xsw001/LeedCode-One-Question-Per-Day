//
// @lc app=leetcode.cn id=900 lang=java
//
// [900] reordered-power-of-2
//
class Solution {
    Set<String> powerOf2Digits = new HashSet<String>();

    public boolean reorderedPowerOf2(int n) {
        init();
        return powerOf2Digits.contains(countDigits(n));
    }

    public void init() {
        for (int n = 1; n <= 1e9; n <<= 1) {
            powerOf2Digits.add(countDigits(n));
        }
    }

    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }
}

// @lc code=end