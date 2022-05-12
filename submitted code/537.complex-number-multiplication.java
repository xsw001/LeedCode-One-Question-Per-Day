//
// @lc app=leetcode.cn id=537 lang=java
//
// [537] complex-number-multiplication
//
class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        String[] ab = num1.split("\\+");
        String[] cd = num2.split("\\+");
        int a = Integer.parseInt(ab[0]);
        int b = Integer.parseInt(ab[1].substring(0,ab[1].length()-1));
        int c = Integer.parseInt(cd[0]);
        int d = Integer.parseInt(cd[1].substring(0,cd[1].length()-1));
        return String.valueOf(a * c - b * d) + '+' + (a * d + c * b) + 'i';
    }
}
// @lc code=end