//
// @lc app=leetcode.cn id=890 lang=java
//
// [890] lemonade-change
//
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] charCount = new int[3];

        for (int bill : bills) {
            if (bill == 5)
                charCount[0]++;
            if (bill == 10) {
                charCount[1]++;
                charCount[0]--;
                if(charCount[0]<0)
                    return false;
            }
            if (bill == 20) {
                charCount[2]++;
                charCount[0]--;
                if (charCount[1] == 0)
                    charCount[0] -= 2;
                else
                    charCount[1]--;
                if (charCount[0] < 0 || charCount[1] < 0)
                    return false;
            }
        }
        return true;
    }
}
// @lc code=end