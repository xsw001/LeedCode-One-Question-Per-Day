//
// @lc app=leetcode.cn id=437 lang=java
//
// [437] path-sum-iii
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
    static int ans;

    public static int pathSum(TreeNode root, int targetSum) {
        ans = 0;
        if (root != null)
            dfs(root, targetSum);
        return ans;
    }

    private static void dfs(TreeNode root, int targetSum) {
        dfs1(root, targetSum);
        if (root.left != null)
            dfs(root.left, targetSum);
        if (root.right != null)
            dfs(root.right, targetSum);
    }

    private static void dfs1(TreeNode root, int sum) {
        if (root == null)
            return;
        if (root.val == sum)
            ++ans;
        dfs1(root.left, sum - root.val);
        dfs1(root.right, sum - root.val);
    }
}
// @lc code=end