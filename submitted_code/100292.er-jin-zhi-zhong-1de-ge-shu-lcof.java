//
// @lc app=leetcode.cn id=100292 lang=java
//
// [100292] er-jin-zhi-zhong-1de-ge-shu-lcof
//
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans =0 ;
        while(n != 0){
            if( (n & 1) != 0)
                ++ans;
            n = n >>> 1;
        }
        return ans;
    }
}
// @lc code=end