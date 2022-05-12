//
// @lc app=leetcode.cn id=100317 lang=java
//
// [100317] er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
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
    List<List<Integer>> ans;
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ans = new ArrayList<>();
        dfs(root,target,new ArrayList<Integer>());
        return ans;
    }

    public void dfs(TreeNode root, int target, ArrayList<Integer> list){
        if (root == null)
            return;
        int val = root.val;
        list.add(val);
        if (target == val && root.left == null && root.right == null)
            ans.add(new ArrayList<>(list));
        
        dfs(root.left, target - val, list);
        dfs(root.right, target - val, list);
        list.remove(list.size() - 1);
    }
}
// @lc code=end