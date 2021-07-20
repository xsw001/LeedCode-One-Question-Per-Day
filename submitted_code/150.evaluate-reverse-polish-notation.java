//
// @lc app=leetcode.cn id=150 lang=java
//
// [150] evaluate-reverse-polish-notation
//
class Solution {
    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> list = new LinkedList<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int a = list.pollFirst();
                int b = list.pollFirst();
                int c = priority(token, a, b);
                list.offerFirst(c);
            } else
                list.offerFirst(turn(token));
        }
        return list.peekFirst();
    }

    private static int turn(String s) {
                if (s == null) {
            throw new NumberFormatException("null");
        }

        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;

        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            }
            i++;
        }
        int multmin = limit / 10;
        int result = 0;
        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int digit = Character.digit(s.charAt(i++), 10);
            result *= 10;
            result -= digit;
        }
        return negative ? result : -result;
    }

    public static int priority(String s, int a, int b) {
        if (s.equals("+"))
            return a + b;
        if (s.equals("-"))
            return b - a;
        if (s.equals("*"))
            return a * b;
        return b / a;
    }
}
// @lc code=end