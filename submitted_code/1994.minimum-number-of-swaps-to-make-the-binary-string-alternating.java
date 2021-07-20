//
// @lc app=leetcode.cn id=1994 lang=java
//
// [1994] minimum-number-of-swaps-to-make-the-binary-string-alternating
//
class Solution {
    public static int minSwaps(String s) {
        int one = 0;
        int zero = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ++one;
            } else {
                ++zero;
            }
        }
        if (Math.abs(one - zero) > 1)
            return -1;
        if (one > zero)
            return count(s,"10");
        else if (one < zero)
            return count(s,"01");
        else
            return Math.min(count(s,"10"),count(s,"01"));
    }

    private static int count(String s, String sub) {
        String str = "";
        for (int i = 0; i < s.length() / 2; i++) {
            str += sub;
        }
        if (s.length() % 2 == 1) {
            str += sub.charAt(0) + "";
        }

        System.out.println(str);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != str.charAt(i))
                ++res;
        }
        return (res + 1) / 2;
    }
}
// @lc code=end