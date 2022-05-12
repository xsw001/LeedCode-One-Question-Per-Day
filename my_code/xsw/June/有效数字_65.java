package xsw.June;
/*
65. 有效数字
有效数字（按顺序）可以分成以下几个部分：

一个 小数 或者 整数
（可选）一个 'e' 或 'E' ，后面跟着一个 整数
小数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
下述格式之一：
至少一位数字，后面跟着一个点 '.'
至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
一个点 '.' ，后面跟着至少一位数字
整数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
至少一位数字
部分有效数字列举如下：

["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
部分无效数字列举如下：

["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。



示例 1：

输入：s = "0"
输出：true
示例 2：

输入：s = "e"
输出：false
示例 3：

输入：s = "."
输出：false
示例 4：

输入：s = ".1"
输出：true


提示：

1 <= s.length <= 20
s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
通过次数27,961提交次数120,749
 */

public class 有效数字_65 {

    public static boolean isNumber(String s) {
        int l = s.length();
        // e 不能在开头末尾
        if (s.charAt(0) == 'e' || s.charAt(l - 1) == 'e' || s.charAt(0) == 'E' || s.charAt(l - 1) == 'E' || s.charAt(l - 1) == '+' || s.charAt(l - 1) == '-')
            return false;
        if (l == 1 && s.charAt(0) == '.')
            return false;
        // 不能有其它字母
        int e = 0;
        int E = 0;
        int point = 0;
        int pos = 0;
        int neg = 0;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (s.charAt(i) == '+') ++pos;
                else ++neg;
            }
            if (Character.isLetter(s.charAt(i))) {
                if (s.charAt(i) != 'e' && s.charAt(i) != 'E')
                    return false;
                else {
                    if (s.charAt(i) == 'e') ++e;
                    else ++E;
                    if (e + E > 1)
                        return false;
                }
            }
            if (s.charAt(i) == '.') {
                ++point;
                if (point > 1)
                    return false;
            }
        }
        if (pos + neg > 2)
            return false;
        if (point == 1) {
            int i = s.indexOf('.');
            int flag = 0;
            if (i > 0 && Character.isDigit(s.charAt(i - 1))) ++flag;
            if (i < l - 1 && Character.isDigit(s.charAt(i + 1))) ++flag;
            if (flag == 0)
                return false;
        }
        // e 后边不能有小数，必须有整数
        if (e + E == 1) {
            int i = s.indexOf(e == 0 ? 'E' : 'e');
            if (point == 1) {
                int j = s.indexOf('.');
                if (j > i)
                    return false;
            }
            if (pos == 1) {
                if (symbol(s, i, '+'))
                    return false;
            }
            if (neg == 1) {
                return !symbol(s, i, '-');
            }
        } else {
            if (pos + neg > 1)
                return false;
            else if (pos + neg == 1) {
                return s.indexOf(pos == 0 ? '-' : '+') == 0;
            }
        }
        return true;
    }

    public static boolean symbol(String s, int i, char c) {
        int k = s.indexOf(c);
        if (k != 0) {
            if (k < i || k == s.length() - 1)
                return true;
            else {
                return k - 1 != i;
            }
        } else
            return k + 1 == i;
    }

    public static void main(String[] args) {
        System.out.println(isNumber("2+.6e+"));
        String[] s = {"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"
                , "abc", "1a", "1e", "+e3", "99e2.5", "--6", "-+3", "95a54e53", ".e1", "6+1", "56e+0"};
        for (String s1 : s) {
            if (isNumber(s1))
                System.out.println(s1);
        }

    }

}