//
// @lc app=leetcode.cn id=1035 lang=java
//
// [1035] cousins-in-binary-tree
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static boolean isCousins(TreeNode root, int x, int y) {
        if (dfs(root, x) == dfs(root, y))
            return false;
        return deep(root, x, 0) == deep(root, y, 0);
    }

    public static TreeNode dfs(TreeNode root, int n) {
        if (root == null) return null;
        if (root.left != null && root.left.val == n) {
            return root;
        }
        if (root.right != null && root.right.val == n) {
            return root;
        }
        TreeNode left = dfs(root.left, n);
        TreeNode right = dfs(root.right, n);

        return left == null ? right : left;
    }

    public static int deep(TreeNode root, int node, int h) {
        if (root == null)
            return 0;
        if (root.val == node) {
            return h;
        }
        int a = deep(root.right, node, h + 1);
        int b = deep(root.left, node, h + 1);
        return Math.max(a, b);
    }
}
// @lc code=end