//
// @lc app=leetcode.cn id=1933 lang=java
//
// [1933] number-of-different-integers-in-a-string
//
class Solution {
    public int numDifferentIntegers(String word) {
        HashSet<String> set = new HashSet<>();
        int l = word.length();
        for (int i = 0; i < l; ) {
            if (word.charAt(i) <= '9' && word.charAt(i) >= '0') {//是数字
                StringBuilder num = new StringBuilder();
                while (i < l && word.charAt(i) <= '9' && word.charAt(i) >= '0') {
                    num.append(word.charAt(i));
                    ++i;
                }
                int j = 0;
                while (j < num.length()) {
                    if (num.charAt(j) != '0')
                        break;
                    ++j;
                }
                set.add(num.substring(j));
            } else i++;
        }
        return set.size();
    }
}
// @lc code=end