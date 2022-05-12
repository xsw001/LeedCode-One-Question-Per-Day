//
// @lc app=leetcode.cn id=100305 lang=java
//
// [100305] er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
//
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    List<Node> nodes;

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return root;
        nodes = new ArrayList<>();
        dfs(root);
        int size = nodes.size();
        if (size == 1) {
            nodes.get(0).right = nodes.get(0);
            nodes.get(0).left = nodes.get(0);
        } else {
            for (int i = 1; i < size - 1; i++) {
                nodes.get(i).left = nodes.get(i - 1);
                nodes.get(i).right = nodes.get(i + 1);
            }
            nodes.get(0).right = nodes.get(1);
            nodes.get(0).left = nodes.get(size - 1);
            nodes.get(size - 1).right = nodes.get(0);
            nodes.get(size - 1).left = nodes.get(size - 2);
        }
        return nodes.get(0);
    }

    private void dfs(Node root) {
        if (root == null)
            return;
        dfs(root.left);
        nodes.add(root);
        dfs(root.right);
    }
}
// @lc code=end