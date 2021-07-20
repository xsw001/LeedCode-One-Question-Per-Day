//
// @lc app=leetcode.cn id=933 lang=java
//
// [933] increasing-order-search-tree
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
    static ArrayList<Integer> list = new ArrayList<Integer>();

    public static TreeNode increasingBST(TreeNode root) {
        TreeNode res = new TreeNode();
        TreeNode temp = res;
        help(root);
        ArrayList<Integer> li = new ArrayList<Integer>(list);
        list.clear();
        for (Integer value : li) {
            TreeNode node = new TreeNode(value);
            temp.left = null;
            temp.right = node;
            temp = node;
        }
        return res.right;
    }

    public static void help(TreeNode root) {
        if (root == null)
            return;
        help(root.left);
        list.add(root.val);
        help(root.right);
    }
}
// @lc code=end