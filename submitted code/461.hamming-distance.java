//
// @lc app=leetcode.cn id=461 lang=java
//
// [461] hamming-distance
//
class Solution {
    public int hammingDistance(int x, int y) {
        int res = 0;
        for(int i =0;i<32;++i){
            res += (((x>>i)&1)^((y>>i)&1));
        }
        return res;
    }
}
// @lc code=end