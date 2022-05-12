//
// @lc app=leetcode.cn id=476 lang=java
//
// [476] number-complement
//
class Solution {
    public int findComplement(int num) {
        int i = 0;
        int ans = 0;
        while(num > 0){
            int t = num & 1;
            num = num >> 1;
            if(t == 0)
                ans += Math.pow(2,i);
            ++i;
        }
        return ans;
    }
}
// @lc code=end