//
// @lc app=leetcode.cn id=1321 lang=java
//
// [1321] get-equal-substrings-within-budget
//
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int maxLength = 0,temp = 0,maxCostTemp = maxCost;
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == t.charAt(i))
                ++temp;
            else{
                int cost = Math.abs(s.charAt(i) - t.charAt(i));
                if (maxCost != 0 && cost <= maxCost){
                    maxCost -= cost;
                    ++temp;
                }else {
                    maxLength = Math.max(maxLength,temp);
                    maxCost = maxCostTemp;
                    temp = 0;
                }
            }
        }
        return Math.max(maxLength,temp);
    }
}
// @lc code=end