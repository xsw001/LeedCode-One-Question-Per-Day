//
// @lc app=leetcode.cn id=563 lang=java
//
// [563] binary-tree-tilt
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
    int ans = 0;
    public int findTilt(TreeNode root) {
        if(root == null)
            return 0;
        return Math.abs(dfs(root.left)-dfs(root.right)) + findTilt(root.left) + findTilt(root.right);
    }

    public int dfs(TreeNode root) {
        if(root == null)
            return 0;
        return root.val + dfs(root.left) + dfs(root.right);  
    }
}
// @lc code=end