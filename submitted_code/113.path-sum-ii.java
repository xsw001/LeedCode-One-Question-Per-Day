//
// @lc app=leetcode.cn id=113 lang=java
//
// [113] path-sum-ii
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
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        method(root,sum);
        return ret;
    }

    public void method(TreeNode root, int sum){
        if(root == null)
            return;
        path.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        method(root.left, sum);
        method(root.right, sum);
        path.pollLast();
    }
}
// @lc code=end