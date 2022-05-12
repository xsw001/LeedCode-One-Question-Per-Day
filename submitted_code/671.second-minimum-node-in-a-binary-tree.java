//
// @lc app=leetcode.cn id=671 lang=java
//
// [671] second-minimum-node-in-a-binary-tree
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

    static int a, b;

    public static int findSecondMinimumValue(TreeNode root) {
        a = Integer.MAX_VALUE;
        b = Integer.MAX_VALUE;
        bfs(root);
        return b == Integer.MAX_VALUE ? -1 : b;
    }

    private static int bfs(TreeNode root) {
        if (root.right == null) {
            if(root.val < a){
                b = a;
                a = root.val;
            }else if(root.val > a && root.val < b)
                b = root.val;
            return root.val;
        }
        int value = Math.min(bfs(root.left), bfs(root.right));
        if(value < a){
            b = a;
            a = value;
        }else if(value > a && value < b)
            b = value;
        return Math.min(root.left.val, root.right.val);
    }
}
// @lc code=end