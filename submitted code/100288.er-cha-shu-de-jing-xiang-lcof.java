//
// @lc app=leetcode.cn id=100288 lang=java
//
// [100288] er-cha-shu-de-jing-xiang-lcof
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

    public TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return root;
        TreeNode t = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = t;
        return root;
    }
}
// @lc code=end