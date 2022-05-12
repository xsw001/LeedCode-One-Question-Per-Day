//
// @lc app=leetcode.cn id=100280 lang=java
//
// [100280] ti-huan-kong-ge-lcof
//
class Solution {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();++i){
            if(s.charAt(i) == ' ')
                sb.append("%20");
            else
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
// @lc code=end