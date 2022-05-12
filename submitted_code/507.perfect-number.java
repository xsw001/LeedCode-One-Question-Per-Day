//
// @lc app=leetcode.cn id=507 lang=java
//
// [507] perfect-number
//
class Solution {
    public boolean checkPerfectNumber(int num) {
        if(num == 1)
            return false;
        int sum = 1;
        for(int i = 2; i <= Math.sqrt(num); ++i){
            if(num % i == 0)
                sum += i + num / i;
        }
        return sum == num;
    }
}
// @lc code=end