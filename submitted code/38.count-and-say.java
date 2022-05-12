//
// @lc app=leetcode.cn id=38 lang=java
//
// [38] count-and-say
//
class Solution {
    public String countAndSay(int n) {
        String ans = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int j = 0;
            while(j < ans.length()){
                int k = j;
                while(j < ans.length()-1 && ans.charAt(j) == ans.charAt(j+1))
                    ++j;
                sb.append(j-k+1).append(ans.charAt(k));
                ++j;
            }
            ans = sb.toString();
        }
        return ans;
    }
}
// @lc code=end