//
// @lc app=leetcode.cn id=774 lang=java
//
// [774] maximum-depth-of-n-ary-tree
//
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {

    public int maxDepth(Node root) {
        if(root == null)
            return  0;
        LinkedList<Node> list = new LinkedList<>();
        list.addFirst(root);
        int ans = 0;
        while(!list.isEmpty()){
            int n = list.size();
            while(n > 0){
                Node node = list.pollLast();
                for (Node child : node.children) {
                    list.addFirst(child);
                }
                --n;
            }
            ++ans;
        }
        return  ans;
    }
}
// @lc code=end