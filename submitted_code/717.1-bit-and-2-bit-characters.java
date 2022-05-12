//
// @lc app=leetcode.cn id=717 lang=java
//
// [717] 1-bit-and-2-bit-characters
//
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int l = bits.length-1;
        int i = 0;
        while(i < l){
            if(bits[i] == 0){
                ++i;
            }else{
                i+=2;
            }
        }
        return i == l;
    }
}
// @lc code=end