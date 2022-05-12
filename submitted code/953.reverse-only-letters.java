//
// @lc app=leetcode.cn id=953 lang=java
//
// [953] reverse-only-letters
//
class Solution {
    public String reverseOnlyLetters(String s) {
        char[] array = s.toCharArray();
        int l = 0, r = array.length - 1;
        while (l < r) {
            while (l < r && !Character.isLetter(array[l])) ++l;
            while (l < r && !Character.isLetter(array[r])) --r;
            char t = array[l];
            array[l++] = array[r];
            array[r--] = t;
        }
        return new String(array);
    }
}
// @lc code=end