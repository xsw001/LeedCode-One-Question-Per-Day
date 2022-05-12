//
// @lc app=leetcode.cn id=100342 lang=java
//
// [100342] ping-heng-er-cha-shu-lcof
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
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        if(Math.abs(deep(root.left) - deep(root.right)) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int deep(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(deep(root.left),deep(root.right)) + 1;
    }
}
// @lc code=end