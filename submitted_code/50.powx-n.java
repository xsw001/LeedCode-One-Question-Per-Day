//
// @lc app=leetcode.cn id=50 lang=java
//
// [50] powx-n
//
class Solution {
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        long N = n > 0 ? n : -n;
        double res = myPowHelp(x,N);
        return n < 0 ? 1/res : res;
    }

    public double myPowHelp(double x, long n) {
        if(n == 0)
            return 1;
        if(n % 2 == 0){
            double t = myPowHelp(x,n/2);
            return t*t;
        }else{
            double t = myPowHelp(x,n/2);
            return t*t*x;
        }
    }
}
// @lc code=end