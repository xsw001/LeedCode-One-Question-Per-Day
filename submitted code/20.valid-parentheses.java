//
// @lc app=leetcode.cn id=20 lang=java
//
// [20] valid-parentheses
//
class Solution {
    public boolean isValid(String s) {
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
}
// @lc code=end