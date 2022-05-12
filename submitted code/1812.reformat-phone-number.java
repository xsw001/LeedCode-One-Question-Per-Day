//
// @lc app=leetcode.cn id=1812 lang=java
//
// [1812] reformat-phone-number
//
class Solution {
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != '-' && number.charAt(i) != ' ') {
                sb.append(number.charAt(i));
            }
        }
        int len = sb.length();
        int j = 0;
        if (len % 3 != 1) {
            for (int i = 0; i < sb.length(); i++) {
                ++j;
                if (j == 3 && i < sb.length()-1) {
                    sb.insert(i + 1, '-');
                    ++i;
                    j = 0;
                }
            }
            return sb.toString();
        }else{
            for (int i = 0; i < sb.length(); i++) {
                ++j;
                if (j == 3) {
                    sb.insert(i + 1, '-');
                    ++i;
                    j = 0;
                }
                if(i==sb.length()-4)
                    break;
            }
            sb.insert(sb.length()-2, '-');
            return sb.toString();
        }
    }
}
// @lc code=end