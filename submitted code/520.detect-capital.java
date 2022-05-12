//
// @lc app=leetcode.cn id=520 lang=java
//
// [520] detect-capital
//
class Solution {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        int num = 0;
        for(int i =0; i < n;++i){
            if(word.charAt(i)>='A' && word.charAt(i) <= 'Z')
                ++num;
            else if(num > 1)
                return false;
        }
        return num==0 || num==n || (num==1 && word.charAt(0)>='A' && word.charAt(0) <= 'Z');
    }
}
// @lc code=end