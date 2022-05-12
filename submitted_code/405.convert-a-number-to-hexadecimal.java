//
// @lc app=leetcode.cn id=405 lang=java
//
// [405] convert-a-number-to-hexadecimal
//
class Solution {
    public String toHex(int _num) {
        if (_num == 0) return "0";
        long num = _num;
        StringBuilder sb = new StringBuilder();
        if(num < 0) num = (long)(Math.pow(2, 32) + num);
        while (num != 0) {
            long u = num % 16;
            char c = (char)(u + '0');
            if (u >= 10) c = (char)(u - 10 + 'a');
            sb.append(c);
            num /= 16;
        }
        return sb.reverse().toString();
    }
}

// @lc code=end