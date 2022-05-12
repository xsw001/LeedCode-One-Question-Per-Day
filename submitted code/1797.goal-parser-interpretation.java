//
// @lc app=leetcode.cn id=1797 lang=java
//
// [1797] goal-parser-interpretation
//
class Solution {
    public String interpret(String command) {
        int l = command.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            char c = command.charAt(i);
            if(c =='G') {
                sb.append(c);
                continue;
            }
            if(c==')'){
                if(command.charAt(i-1)=='(')
                    sb.append('o');
                else {
                    sb.append('a');
                    sb.append('l');
                }
            }

        }
        return sb.toString();
    }
}
// @lc code=end