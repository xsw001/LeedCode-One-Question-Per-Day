//
// @lc app=leetcode.cn id=17 lang=java
//
// [17] letter-combinations-of-a-phone-number
//
class Solution {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> ans = new ArrayList<>();
        if (digits.equals(""))
            return ans;
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        int l = digits.length();
        for (char c : map.get(digits.charAt(0)).toCharArray()) {
            ans.add(String.valueOf(c));
        }
        for (int i = 1; i < l; i++) {
            ArrayList<String> list = new ArrayList<>();
            for (char c1 : map.get(digits.charAt(i)).toCharArray()) {
                for (String an : ans) {
                    list.add(an + c1);
                }
            }
            ans = new ArrayList<>(list);
        }

        return ans;
    }
}
// @lc code=end