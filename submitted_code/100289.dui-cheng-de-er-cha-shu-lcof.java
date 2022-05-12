//
// @lc app=leetcode.cn id=100289 lang=java
//
// [100289] dui-cheng-de-er-cha-shu-lcof
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
    public boolean isSymmetric(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return true;
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode l, TreeNode r) {
        if(l == null && r ==null)
            return true;
        if(l==null || r==null || l.val != r.val)
            return false;
        return dfs(l.left, r.right) &&  dfs(r.left, l.right);
    }
}
// @lc code=end