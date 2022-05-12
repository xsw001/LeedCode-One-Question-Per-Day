//
// @lc app=leetcode.cn id=168 lang=java
//
// [168] excel-sheet-column-title
//
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}

// @lc code=end