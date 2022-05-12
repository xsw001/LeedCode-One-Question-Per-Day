//
// @lc app=leetcode.cn id=22 lang=java
//
// [22] generate-parentheses
//
class Solution {
    List<String> ans;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        dfs(new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void dfs(StringBuilder sb, int l, int r, int n) {
        if (sb.length() == n * 2) {
            ans.add(sb.toString());
            return;
        }
        if (l < n) {
            sb.append('(');
            dfs(sb, l + 1, r, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (r < l) {
            sb.append(')');
            dfs(sb, l, r + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
// @lc code=end