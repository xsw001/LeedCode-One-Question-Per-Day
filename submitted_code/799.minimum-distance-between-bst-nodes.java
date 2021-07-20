//
// @lc app=leetcode.cn id=799 lang=java
//
// [799] minimum-distance-between-bst-nodes
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
    public int minDiffInBST(TreeNode root) {
        TreeSet<Integer> set = new TreeSet<>();
        dfs(root,set);
        int res = 10001;
        int a = set.pollFirst();
        int b = set.pollFirst();
        res = Math.min(res,b-a);
        while(!set.isEmpty()){
            a = b;
            b = set.pollFirst();
            res = Math.min(res,b-a);
        }
        return res;
    }

    private static void dfs(TreeNode root,TreeSet<Integer> set) {
        if (root == null)
            return;
        set.add(root.val);
        dfs(root.left,set);
        dfs(root.right,set);
    }
}
// @lc code=end