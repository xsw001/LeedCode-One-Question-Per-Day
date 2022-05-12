//
// @lc app=leetcode.cn id=504 lang=java
//
// [504] base-7
//
class Solution {
    public String convertToBase7(int num) {
        if(num == 0)
            return "0";
        boolean f = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.append(num % 7);
            num /= 7;
        }
        StringBuilder ans = new StringBuilder();
        if(f)
            ans.append('-');
        return ans.append(sb.reverse()).toString();
    }
}
// @lc code=end