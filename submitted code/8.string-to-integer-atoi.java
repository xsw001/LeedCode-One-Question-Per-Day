//
// @lc app=leetcode.cn id=8 lang=java
//
// [8] string-to-integer-atoi
//
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if(s.equals(""))
            return 0;
        if (s.charAt(0) == '.' || Character.isLetter(s.charAt(0)))
            return 0;
        char[] chars = s.toCharArray();
        boolean flag = false;
        int index = 0;
        if (chars[0] == '-') {
            flag = true;
            ++index;
        } else if (chars[0] == '+')
            ++index;
        long ans = 0;
        while (index < chars.length) {
            if (Character.isDigit(chars[index])) {
                ans = ans * 10 + (chars[index++] - '0');
                if(ans > 21474836470L)
                    break;
            }else
                break;
        }
        if(flag)
            ans *= -1;
        if(ans > 2147483647)
            ans = 2147483647;
        if(ans < -2147483648)
            ans = -2147483648;
        return (int) ans;
    }
}
// @lc code=end