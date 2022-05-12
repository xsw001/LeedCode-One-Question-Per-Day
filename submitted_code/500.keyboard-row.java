//
// @lc app=leetcode.cn id=500 lang=java
//
// [500] keyboard-row
//
class Solution {
    static String[] keyboard = {"qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};

    public String[] findWords(String[] words) {
        ArrayList<String> ans = new ArrayList<>();
        for (String word : words) {
            if (find(word))
                ans.add(word);
        }
        return ans.toArray(new String[0]);
    }

    private boolean find(String word) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < word.length(); i++) {
            if (keyboard[0].indexOf(word.charAt(i)) != -1) {
                ++a;
                if (b != 0 || c != 0)
                    return false;
            } else if (keyboard[1].indexOf(word.charAt(i)) != -1) {
                ++b;
                if (a != 0 || c != 0)
                    return false;
            } else {
                ++c;
                if (b != 0 || a != 0)
                    return false;
            }
        }
        return true;
    }
}
// @lc code=end