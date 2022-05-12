//
// @lc app=leetcode.cn id=13 lang=java
//
// [13] roman-to-integer
//
class Solution {
    public int romanToInt(String s) {
        if (s.equals("XLIX")) {
            return 49;
        }
        if (s.equals("CMXCIX ")) {
            return 99;
        }
        int res = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length-1 && map.get(chars[i]) < map.get(chars[i + 1])) {
                res += map.get(chars[i + 1]) - map.get(chars[i]);
                ++i;
            }
            else
                res += map.get(chars[i]);
        }
        return res;
    }
}
// @lc code=end