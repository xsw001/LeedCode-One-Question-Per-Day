//
// @lc app=leetcode.cn id=144 lang=java
//
// [144] binary-tree-preorder-traversal
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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode[] stack = new TreeNode[100];
        int top=-1;
        TreeNode p = root;
        while (p!=null || top!=-1)
        {
            if (p!=null) {
                stack[++top]=p;
                result.add(p.val);
                p = p.left;
            }
            else
            {
                p=stack[top--];
                p = p.right;
            }
        }
        return result;
    }
}
// @lc code=end