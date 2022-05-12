//
// @lc app=leetcode.cn id=102 lang=java
//
// [102] binary-tree-level-order-traversal
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) {
            return  result;
        }
        ArrayDeque<TreeNode> list = new ArrayDeque<>();
        list.addLast(root);
        ArrayList<Integer> integers = new ArrayList<>();
        TreeNode flag = new TreeNode(Integer.MIN_VALUE);
        list.addLast(flag);
        while(!list.isEmpty()){
            TreeNode p = list.poll();
            if(p != flag){
                integers.add(p.val);
                if(p.left!=null) {
                    list.add(p.left);
                }
                if(p.right!=null) {
                    list.add(p.right);
                }
            }else{
                ArrayList<Integer> temp = new ArrayList<>(integers);
                result.add(temp);
                integers.clear();
                if(!list.isEmpty())
                    list.addLast(flag);
            }
        }
        return result;
    }
}
// @lc code=end