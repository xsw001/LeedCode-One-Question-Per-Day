package xsw.August;
/*
541. 反转字符串 II
给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。

如果剩余字符少于 k 个，则将剩余字符全部反转。
如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。


示例 1：

输入：s = "abcdefg", k = 2
输出："bacdfeg"
示例 2：

输入：s = "abcd", k = 2
输出："bacd"


提示：

1 <= s.length <= 104
s 仅由小写英文组成
1 <= k <= 104
通过次数47,681提交次数80,788
请问您在哪类招聘中遇到此题？
 */

import java.util.ArrayList;

public class 反转字符串II_541 {

    public static String reverseStr(String s, int k) {
        int n = s.length();
        if (n < k)
            return new StringBuilder(s).reverse().toString();
        if (n < k * 2)
            return reverseK(s, k);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index + 2 * k <= n) {
            sb.append(reverseK(s.substring(index, index + 2 * k), k));
            index += 2 * k;
        }
        if (n != sb.length()) {
            String substring = s.substring(sb.length(), n);
            if (n - sb.length() < k)
                sb.append(new StringBuilder(substring).reverse());
            else
                sb.append(reverseK(substring, k));
        }
        return sb.toString();
    }

    private static String reverseK(String s, int k) {
        return new StringBuilder(s.substring(0, k)).reverse().append(s.substring(k)).toString();
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(reverseStr("abcdefg", 3));
    }

}