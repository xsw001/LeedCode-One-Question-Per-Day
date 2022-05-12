//
// @lc app=leetcode.cn id=6 lang=java
//
// [6] zigzag-conversion
//
class Solution {
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows[i].append(c);
            if (i == 0 || i == numRows - 1) 
                flag = -flag;
            i += flag;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row);
        }
        return ans.toString();
    }
}
// @lc code=end