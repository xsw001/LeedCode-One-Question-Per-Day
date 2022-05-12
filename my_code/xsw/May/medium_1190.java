package xsw.May;
/*
1190. 反转每对括号间的子串
给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

注意，您的结果中 不应 包含任何括号。



示例 1：

输入：s = "(abcd)"
输出："dcba"
示例 2：

输入：s = "(u(love)i)"
输出："iloveu"
示例 3：

输入：s = "(ed(et(oc))el)"
输出："leetcode"
示例 4：

输入：s = "a(bcdefghijkl(mno)p)q"
输出："apmnolkjihgfedcbq"


提示：

0 <= s.length <= 2000
s 中只有小写英文字母和括号
我们确保所有括号都是成对出现的
通过次数16,606提交次数26,951
 */

import java.util.*;

public class medium_1190 {

    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
            }
            if (s.charAt(i) == ')') {
                int begin = stack.pollFirst();
                String sub = sb.substring(begin + 1, i);
                sb.replace(begin + 1, i, reverse(sub));
            }
        }
        for (int i = 0; i < sb.length(); ) {
            if (sb.charAt(i) == '(' || sb.charAt(i) == ')')
                sb.deleteCharAt(i);
            else
                i++;
        }
        return sb.toString();
    }

    private static String reverse(String sub) {
        char[] s = sub.toCharArray();
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
        return new String(s);
    }

    public static void main(String[] args) {
        String s = "(ed(et(oc))el)";
        System.out.println(reverseParentheses(s));
    }

    public String reverseParentheses1(String s) {
        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}