//
// @lc app=leetcode.cn id=551 lang=java
//
// [551] student-attendance-record-i
//
class Solution {
    public boolean checkRecord(String s) {
        int a = 0, l = 0;
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) == 'L'){
                ++l;
                if(l >= 3)
                    return false;
            }else{
                l = 0;
                if(s.charAt(i) == 'A'){
                    ++a;
                    if(a > 1)
                        return false;
                }
            }
        }
        return l < 3;
    }
}
// @lc code=end