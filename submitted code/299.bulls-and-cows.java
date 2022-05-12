//
// @lc app=leetcode.cn id=299 lang=java
//
// [299] bulls-and-cows
//
class Solution {
    public String getHint(String secret, String guess) {
        int n = secret.length();
        int a = 0, b = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        boolean[] f = new boolean[n];
        for (int i = 0; i < n; i++) {
            char c = secret.charAt(i);
            if (c == guess.charAt(i)) {
                ++a;
                f[i] = true;
            } else
                map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if(!f[i]) {
                char c = guess.charAt(i);
                if (map.containsKey(c)) {
                    ++b;
                    int num = map.get(c);
                    if (num == 1)
                        map.remove(c);
                    else
                        map.put(c, num - 1);
                }
            }
        }
        return a + "A" + b + "B";
    }
}
// @lc code=end