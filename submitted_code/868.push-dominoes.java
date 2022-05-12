//
// @lc app=leetcode.cn id=868 lang=java
//
// [868] push-dominoes
//
class Solution {
    public String pushDominoes(String s) {
        ArrayList<String> list = new ArrayList<>();
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        int l = 0;
        int r = 0;
        while (l < n) {
            r = l + 1;
            if (s.charAt(l) == '.' || s.charAt(l) == 'L') {
                while (r < n && s.charAt(r) != 'R')
                    ++r;
                ans.append(pushL(s.substring(l, r)));
            } else {
                while (r < n && (s.charAt(r) == '.'))
                    ++r;
                if (r < n && s.charAt(r) == 'L')
                    ++r;
                ans.append(pushR(s.substring(l, r)));
            }
            l = r;
        }
        return ans.toString();
    }

    private String pushR(String s) {
        int n = s.length();
        if (n == 1)
            return s;
        if (s.charAt(n - 1) == '.')
            return "R".repeat(n);
        return "R".repeat(n / 2) + (n % 2 == 0 ? "" : '.') + "L".repeat(n / 2);
    }

    private StringBuilder pushL(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.lastIndexOf('L');
        sb.append("L".repeat(n + 1));
        return sb.append(".".repeat(s.length() - n - 1));
    }
}
// @lc code=end