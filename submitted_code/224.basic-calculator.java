//
// @lc app=leetcode.cn id=224 lang=java
//
// [224] basic-calculator
//
class Solution {
    public static int calculate(String s) {
        if (s.charAt(0) == '-') {
            s = "0" + s;
        }
        String str = mToA(s);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                int a = stack.pop();
                int b = stack.pop();
                if (str.charAt(i) == '+')
                    stack.push(a + b);
                else
                    stack.push(b - a);
            } else {
                stack.push(Integer.parseInt(String.valueOf(str.charAt(i))));
            }
        }
        return stack.peek();
    }

    private static String mToA(String s) {
        Stack<Character> stack = new Stack<Character>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                int n = 0;
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        ++n;
                        if (n > 1)
                            break;
                    } else
                        res.append(stack.pop());
                }
            } else
                res.append(s.charAt(i));
        }
        while (!stack.isEmpty()) {
            Character c = stack.pop();
            if (c != '(')
                res.append(c);
        }
        return res.toString();
    }
}
// @lc code=end