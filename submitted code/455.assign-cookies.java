//
// @lc app=leetcode.cn id=455 lang=java
//
// [455] assign-cookies
//
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int count = 0;
        int i = 0; 
        int j = 0;
        int n = g.length;
        int m = s.length;
        while (i < n && j < m) {
            while (j < m && s[j] < g[i]) {
                j++;
            }
            
            if (j < m) {
                count++;
                i++;
                j++;
            }
        }
        
        return count;
    }
}
// @lc code=end