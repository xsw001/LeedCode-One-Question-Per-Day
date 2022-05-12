//
// @lc app=leetcode.cn id=749 lang=java
//
// [749] shortest-completing-word
//
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        int l = 10000;
        String ans = "";
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c))
                map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (String word : words) {
            if (completingWord(new HashMap<>(map), word) && word.length() < l) {
                l = word.length();
                ans = word;
            }
        }
        return ans;
    }

    private boolean completingWord(HashMap<Character, Integer> map, String word) {
        for (char c : word.toCharArray()) {
            if (map.containsKey(c)) {
                if (map.get(c) == 1)
                    map.remove(c);
                else
                    map.put(c, map.getOrDefault(c, 0) - 1);
            }
        }
        return map.isEmpty();
    }
}
// @lc code=end