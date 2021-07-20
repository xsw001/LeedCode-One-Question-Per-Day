//
// @lc app=leetcode.cn id=69 lang=java
//
// [69] sqrtx
//
class Solution {
    public int mySqrt(int x) {
        if(x == 0)
            return 0;
        int l =1,r=x;
        while(l < r){
            int mid = l+r+1 >> 1;
            if(mid <= x/mid)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }
}
// @lc code=end