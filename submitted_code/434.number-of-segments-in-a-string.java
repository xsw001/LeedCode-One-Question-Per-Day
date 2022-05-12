//
// @lc app=leetcode.cn id=434 lang=java
//
// [434] number-of-segments-in-a-string
//
class Solution {
    public int countSegments(String s) {
        int segmentCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }

        return segmentCount;
    }
}

// @lc code=end