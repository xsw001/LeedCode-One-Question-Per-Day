//
// @lc app=leetcode.cn id=678 lang=java
//
// [678] valid-parenthesis-string
//
class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        Deque<Integer> k = new ArrayDeque<>();
        Deque<Integer> x = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                k.addFirst(i);
            else if (s.charAt(i) == ')') {
                if (k.isEmpty()) {
                    if (x.isEmpty())
                        return false;
                    else {
                        if (i > x.peekFirst())
                            x.pollFirst();
                        else return false;
                    }
                } else
                    k.pollFirst();
            } else
                x.addFirst(i);
        }
        while(!k.isEmpty()){
            if(x.isEmpty())
                return false;
            if(k.pollFirst() > x.pollFirst())
                return false;
        }
        return true;
    }
}
// @lc code=end