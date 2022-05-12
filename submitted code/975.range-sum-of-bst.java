//
// @lc app=leetcode.cn id=975 lang=java
//
// [975] range-sum-of-bst
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

    public static int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }

    private static void dfs(TreeNode root, int low, int high) {
        if (root == null)
            return;
        if (root.val >= low)
            dfs(root.left, low, high);
        if (root.val >= low && root.val <= high) {
            ans += root.val;
        }
        if (root.val <= high)
            dfs(root.right, low, high);
    }
}
// @lc code=end