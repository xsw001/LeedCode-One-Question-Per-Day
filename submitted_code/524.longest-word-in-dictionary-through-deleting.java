//
// @lc app=leetcode.cn id=524 lang=java
//
// [524] longest-word-in-dictionary-through-deleting
//
class Solution {
    public static String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                for (int i = 0; i < o1.length(); i++) {
                    if(o1.charAt(i)== o2.charAt(i))
                        continue;
                    return o1.charAt(i) - o2.charAt(i);
                }
            }
            return o2.length() - o1.length();
        });
        for (String sub : dictionary) {
            if (isSubStr(s, sub))
                return sub;
        }
        return "";
    }

    private static boolean isSubStr(String s, String sub) {
        int a = 0, b = 0;
        while (a < s.length() && b < sub.length()) {
            if (s.charAt(a) == sub.charAt(b))
                ++b;
            ++a;
        }
        return b == sub.length();
    }
}
// @lc code=end