//
// @lc app=leetcode.cn id=100319 lang=java
//
// [100319] er-cha-shu-de-shen-du-lcof
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
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
// @lc code=end