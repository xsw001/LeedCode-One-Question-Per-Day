//
// @lc app=leetcode.cn id=66 lang=java
//
// [66] plus-one
//
class Solution {
    public int[] plusOne(int[] digits) {
        int flag = digits[0];
        int l = digits.length;
        for(int i=l-1; i>=0; --i){
            if(digits[i] == 9)
                digits[i] = 0;
            else{
                digits[i]++;
                break;
            }
        }
        if(flag > digits[0]){
            int[] ans = new int[l+1];
            ans[0] = 1;
            return ans;
        }
        return digits;
    }
}
// @lc code=end