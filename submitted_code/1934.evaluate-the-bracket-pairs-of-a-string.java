//
// @lc app=leetcode.cn id=1934 lang=java
//
// [1934] evaluate-the-bracket-pairs-of-a-string
//
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String, String> map = new HashMap<>();
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                int j = i + 1;
                String key = "";
                while (j < s.length() && s.charAt(j) != ')') {
                    key += s.charAt(j++);
                }
                String value = map.get(key) == null ? "?" : map.get(key);
                res.append(value);
                i = j+1;
            } else{
                res.append(s.charAt(i));
                ++i;
            }
        }
        return res.toString();
    }
}
// @lc code=end