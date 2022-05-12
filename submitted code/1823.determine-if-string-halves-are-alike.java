//
// @lc app=leetcode.cn id=1823 lang=java
//
// [1823] determine-if-string-halves-are-alike
//
class Solution {
    public boolean halvesAreAlike(String s) {
        ArrayList<Character> list = new ArrayList<>();
        Collections.addAll(list, 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2, s.length());
        int numA = 0, numB = 0;
        for (int i = 0; i < a.length(); i++) {
            if(list.contains(a.charAt(i)))
                ++numA;
            if(list.contains(b.charAt(i)))
                ++numB;
        }
        return numA==numB;
    }
}
// @lc code=end