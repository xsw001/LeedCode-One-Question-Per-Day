//
// @lc app=leetcode.cn id=222 lang=java
//
// [222] count-complete-tree-nodes
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
    static int count = 0;
    public static int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        ++count;
        countNodes(root.left);
        countNodes(root.right);
        return count;
    }
}
// @lc code=end