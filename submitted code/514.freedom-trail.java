//
// @lc app=leetcode.cn id=514 lang=java
//
// [514] freedom-trail
//
class Solution {
    public int findRotateSteps(String ring, String key) {
        final int n = ring.length();
        final int[] memo = new int[n];
        final int[] count = new int[26];
        final char[] cs = ring.toCharArray();
        for (char c : cs) { count[c-'a']++; }
        final int[][] positions = new int[26][];
        for (int i = 0; i < 26; i++) { positions[i] = new int[count[i]]; }
        Arrays.fill(count, 0);
        for (int i = 0; i < n; i++) { positions[cs[i]-'a'][count[cs[i]-'a']++] = i; }
        for (int idx : positions[key.charAt(0)-'a']) { memo[idx] = 1+distance(0, idx, n); }
        for (int idx = 1; idx != key.length(); idx++) {
            int il = key.charAt(idx-1)-'a', ic = key.charAt(idx)-'a';
            int[] cur = positions[ic];
            if (il == ic) {
                for (int i : cur) { memo[i]++; }
                continue; // special ops. should be right
            }
            int[] last = positions[il];
            for (int i : cur) {
                int midDistance = Integer.MAX_VALUE; // note. cannot n
                for (int j : last) {
                    midDistance = Math.min(midDistance, memo[j] + distance(i, j, n));
                }
                memo[i] = midDistance+1;
            }
        }
        int res = Integer.MAX_VALUE; // note cannot n
        for (int idx : positions[key.charAt(key.length()-1)-'a']) { res = Math.min(res, memo[idx]); }
        return res;
    }
    
    private int distance(int src, int dest, final int n) {
        if (src < dest) { return Math.min(dest-src, n-dest+src); }
        else { return Math.min(src-dest, n-src+dest); }
    }
    
}
// @lc code=end