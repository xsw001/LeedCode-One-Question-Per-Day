//
// @lc app=leetcode.cn id=357 lang=java
//
// [357] count-numbers-with-unique-digits
//
class Solution {
    public static int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        n = Math.min(n, 10);
        int ans = 10, base = 9, sum = 9;
        for(int i = 1; i < n; ++i){
            ans += sum *= base--;
        }
        return ans;
    }
}
// @lc code=end