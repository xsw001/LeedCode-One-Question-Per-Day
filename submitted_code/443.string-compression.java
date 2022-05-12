//
// @lc app=leetcode.cn id=443 lang=java
//
// [443] string-compression
//
class Solution {
    public int compress(char[] chars) {
        int l = 0, r = 0;
        int n = chars.length;
        while (r < n) {
            int t = r;
            char c = chars[r];
            while (r < n && c == chars[r])
                ++r;
            int num = r - t;
            chars[l++] = c;
            if (num > 1) {
                while (num > -1) {
                    if (num > 9)
                        chars[l++] = Character.forDigit(num / 10, 10);
                    else {
                        chars[l++] = Character.forDigit(num, 10);
                        break;
                    }
                    num = Integer.parseInt(String.valueOf(num).substring(1));
                }
            }
        }
        return l;
    }
}
// @lc code=end