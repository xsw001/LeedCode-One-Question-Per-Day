//
// @lc app=leetcode.cn id=1835 lang=java
//
// [1835] decode-xored-permutation
//
class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int all = 1;
        for(int i=n;i>1;--i){
            all ^= i;
        }
        int other = 0;
        for(int i=1;i<n-1;i+=2)
            other ^= encoded[i];
        int first = other ^ all;
        int[] res = new int[n];
        res[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            res[i+1] = encoded[i] ^ first;
            first = res[i+1];
        }
        return res;
    }
}
// @lc code=end