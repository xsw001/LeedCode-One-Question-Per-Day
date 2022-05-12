//
// @lc app=leetcode.cn id=775 lang=java
//
// [775] n-ary-tree-preorder-traversal
//
class Solution { public List<Integer> preorder(Node root) { List<Integer> res = new ArrayList<>(); helper(root, res); return res; } public void helper(Node root, List<Integer> res) { if (root == null) { return; } res.add(root.val); for (Node ch : root.children) { helper(ch, res); } } }
// @lc code=end