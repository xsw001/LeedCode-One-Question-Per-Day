//
// @lc app=leetcode.cn id=2032 lang=java
//
// [2032] largest-odd-number-in-string
//
class Solution {
    public String largestOddNumber(String num) {
        int index = -1;
        for (int i = num.length()-1; i >= 0; i--) {
            if(Integer.parseInt(num.charAt(i)+"") % 2 == 1) {
                index = i;
                break;
            }
        }
        return num.substring(0,index+1);
    }
}
// @lc code=end