//
// @lc app=leetcode.cn id=2197 lang=java
//
// [2197] decode-the-slanted-ciphertext
//
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();
        if (rows == 1 || l == 0)
            return encodedText;
        char[] chars = encodedText.toCharArray();
        StringBuilder ans = new StringBuilder();
        int c = l / rows;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < rows; j++) {
                int index = j * c + j + i;
                if (index < l)
                    ans.append(chars[index]);
            }
        }
        l = ans.length();
        if (ans.charAt(l - 1) != ' ')
            return ans.toString();

        int i = 0;
        for (int j = 0; j < l; j++) {
            if (ans.charAt(j) == ' ')
                ++i;
            else
                break;
        }
        return " ".repeat(Math.max(0, i)) + ans.toString().trim();
    }
}
// @lc code=end