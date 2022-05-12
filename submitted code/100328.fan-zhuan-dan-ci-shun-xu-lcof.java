//
// @lc app=leetcode.cn id=100328 lang=java
//
// [100328] fan-zhuan-dan-ci-shun-xu-lcof
//
class Solution {
    public String reverseWords(String s) {
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=ss.length-1;i>=0;--i){
            if(!ss[i].equals("") && ss[i].length() > 0)
                sb.append(ss[i]).append(' ');
        }
        return sb.toString().trim();
    }
}
// @lc code=end