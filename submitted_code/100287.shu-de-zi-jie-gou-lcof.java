//
// @lc app=leetcode.cn id=100287 lang=java
//
// [100287] shu-de-zi-jie-gou-lcof
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

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null)
            return false;
        return help(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }

    public boolean help(TreeNode A, TreeNode B) {
        if(B == null)
            return true;
        if(A == null || A.val != B.val)
            return false;
        return help(A.left,B.left) && help(A.right,B.right);
    }

}
// @lc code=end