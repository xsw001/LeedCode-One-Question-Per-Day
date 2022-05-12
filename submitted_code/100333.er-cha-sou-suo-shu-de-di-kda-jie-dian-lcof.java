//
// @lc app=leetcode.cn id=100333 lang=java
//
// [100333] er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int ans, k;

    public int kthLargest(TreeNode root, int k) {
        ans = 0;
        this.k = k-1;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null || ans != 0 || k < 0)
            return;

        if (root.right != null)
            dfs(root.right);
        if (k == 0) {
            ans = root.val;
        }
        --k;
        if (root.left != null)
            dfs(root.left);
    }
}
// @lc code=end