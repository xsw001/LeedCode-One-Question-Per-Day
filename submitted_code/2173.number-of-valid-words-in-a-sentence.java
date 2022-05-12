//
// @lc app=leetcode.cn id=2173 lang=java
//
// [2173] number-of-valid-words-in-a-sentence
//
class Solution {
    public int countValidWords(String sentence) {
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        Collections.addAll(set, '!', ',', '.');
        String[] split = sentence.split(" ");
        for (String s : split) {
            int l = s.length();
            if (l > 0) {
                int i = 0;
                boolean f = false;
                for (; i < l; i++) {
                    if (s.charAt(i) == '-') {
                        if (!f && i > 0 && i < l - 1 && Character.isLetter(s.charAt(i - 1)) && Character.isLetter(s.charAt(i + 1)))
                            f = true;
                        else
                            break;
                    }
                    if (Character.isDigit(s.charAt(i)))
                        break;
                    if (set.contains(s.charAt(i)) && i != l - 1)
                        break;
                }
                if (l == i)
                    ++ans;
            }
        }
        return ans;
    }
}
// @lc code=end