//
// @lc app=leetcode.cn id=1406 lang=cpp
//
// [1406] subtract-the-product-and-sum-of-digits-of-an-integer
//
class Solution {
public:
    int subtractProductAndSum(int n) {
        int pro = 1, sum = 0;
        while(n!=0){
            pro = pro * (n % 10);
            sum = sum + (n % 10);
            n = n / 10;
        }
        return pro-sum;
    }
};
// @lc code=end