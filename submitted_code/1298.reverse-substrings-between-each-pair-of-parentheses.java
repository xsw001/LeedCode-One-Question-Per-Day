//
// @lc app=leetcode.cn id=1298 lang=java
//
// [1298] reverse-substrings-between-each-pair-of-parentheses
//
class Solution {
    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        int layer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
                ++layer;
            }
            if (s.charAt(i) == ')') {
                int begin = stack.pollFirst();
                --layer;
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
}
// @lc code=end