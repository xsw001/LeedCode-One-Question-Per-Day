//
// @lc app=leetcode.cn id=306 lang=java
//
// [306] additive-number
//
class Solution {
public boolean isAdditiveNumber(String num) {
        int l = num.length();
        for (int i = 0; i < l / 2 + 1; i++) {
            int j = i + 1;
            for (int k = 1; k + j < l; k++) {
                String a = num.substring(0, j);
                String b = num.substring(j, j + k);
                if ((b.startsWith("0") && b.length() > 1) || k > l / 2 + 1)
                    break;
                StringBuilder sb = new StringBuilder();
                sb.append(a).append(b);
                while (sb.length() < l) {
                    String t = sum(a, b);
                    sb.append(t);
                    if (sb.length() > l || !sb.toString().equals(num.substring(0, sb.length())))
                        break;
                    a = b;
                    b = t;
                }
                if (sb.toString().equals(num))
                    return true;
            }
        }
        return false;
    }

    private String sum(String a, String b) {
        int carry = 0;
        if (a.length() > b.length())
            b = "0".repeat(a.length() - b.length()) + b;
        else if (b.length() > a.length())
            a = "0".repeat(b.length() - a.length()) + a;
        int l = a.length();
        StringBuilder builder = new StringBuilder();
        for (int i = l - 1; i >= 0; i--) {
            int c = a.charAt(i) - '0' + b.charAt(i) - '0' + carry;
            builder.append(c % 10);
            carry = c / 10;
        }
        if(carry == 1)
            builder.append(1);
        return builder.reverse().toString();
    }
}
// @lc code=end