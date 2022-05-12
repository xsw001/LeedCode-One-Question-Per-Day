//
// @lc app=leetcode.cn id=367 lang=java
//
// [367] valid-perfect-square
//
class Solution {
    public boolean isPerfectSquare(int num) {
        if(num < 2)
            return true;
        int l = 0, r = num/2;
        while(l < r){
            int mid = (l + r + 1) / 2;
            if(mid > num / mid)
                r = mid - 1;
            else
                l = mid;
        }
        return l * l== num;
    }
}
// @lc code=end