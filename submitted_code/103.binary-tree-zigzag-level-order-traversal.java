//
// @lc app=leetcode.cn id=103 lang=java
//
// [103] binary-tree-zigzag-level-order-traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> list = new ArrayDeque<>();
        list.add(root);
        int flag = 0;
        while (!list.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int len = list.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = list.poll();
                level.add(node.val);
                if (node.left != null)
                    list.add(node.left);
                if (node.right != null)
                    list.add(node.right);
            }
            ++flag;
            if (flag % 2 == 0) {
                Collections.reverse(level);
            }
            result.add(level);
        }
        return result;
    }
}
// @lc code=end