package xsw.October;
/*
20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。


示例 1：

输入：s = "()"
输出：true
示例 2：

输入：s = "()[]{}"
输出：true
示例 3：

输入：s = "(]"
输出：false
示例 4：

输入：s = "([)]"
输出：false
示例 5：

输入：s = "{[]}"
输出：true


提示：

1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
通过次数811,676提交次数1,823,139
 */

import java.util.ArrayDeque;
import java.util.*;

public class 有效的括号_20 {

    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1)
            return false;
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                deque.addFirst('(');
            }
            else if (s.charAt(i) == ')') {
                if(deque.isEmpty() || deque.peek() != '(')
                    return false;
                deque.poll();
            }
            else if (s.charAt(i) == '[') {
                deque.addFirst('[');
            }
            else if (s.charAt(i) == ']'){
                if(deque.isEmpty() || deque.peek() != '[')
                    return false;
                deque.poll();
            }
            else if (s.charAt(i) == '{') {
                deque.addFirst('{');
            }
            else {
                if(deque.isEmpty() || deque.peek() != '{')
                    return false;
                deque.poll();
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(isValid("([)]"));
    }

}