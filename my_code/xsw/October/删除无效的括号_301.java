package xsw.October;
/*
301. 删除无效的括号
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。



示例 1：

输入：s = "()())()"
输出：["(())()","()()()"]
示例 2：

输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
示例 3：

输入：s = ")("
输出：[""]


提示：

1 <= s.length <= 25
s 由小写英文字母以及括号 '(' 和 ')' 组成
s 中至多含 20 个括号
通过次数35,735提交次数67,521
 */

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class 删除无效的括号_301 {
    private static List<String> res = new ArrayList<String>();

    public static List<String> removeInvalidParentheses(String s) {
        int lremove = 0;
        int rremove = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lremove++;
            } else if (s.charAt(i) == ')') {
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }
        helper(s, 0, lremove, rremove);

        return res;
    }

    private static void helper(String str, int start, int lremove, int rremove) {
        if (lremove == 0 && rremove == 0) {
            if (isValid(str))
                res.add(str);
            return;
        }
        for (int i = start; i < str.length(); i++) {
            if (i != start && str.charAt(i) == str.charAt(i - 1))
                continue;
            if (lremove + rremove > str.length() - i)
                return;
            String s = str.substring(0, i) + str.substring(i + 1);
            if (lremove > 0 && str.charAt(i) == '(')
                helper(s, i, lremove - 1, rremove);
            if (rremove > 0 && str.charAt(i) == ')')
                helper(s, i, lremove, rremove - 1);
        }
    }

    private static boolean isValid(String str) {
        int l = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                ++l;
            else if (str.charAt(i) == ')') {
                --l;
                if (l < 0)
                    return false;
            }
        }
        return l == 0;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        String s = "(a)())()";
        System.out.println(removeInvalidParentheses(s));

    }


}