//
// @lc app=leetcode.cn id=1610 lang=java
//
// [1610] xor-operation-in-an-array
//
class Solution {
    public int xorOperation(int n, int start) {
        int res = start;
        for(int i=1;i<n;++i){
            start += 2; 
            res ^= start;
        }
        return res;
    }
}
// @lc code=end