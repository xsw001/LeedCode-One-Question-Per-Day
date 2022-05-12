//
// @lc app=leetcode.cn id=258 lang=java
//
// [258] add-digits
//
class Solution {
    public int addDigits(int num) {
        while(num > 9){
            int t = 0;
            while(num != 0){
                t += num % 10;
                num /= 10;
            }
            num = t;
        }
        return num;
    }
}
// @lc code=end