package Tiger2022.offer;
/*
剑指 Offer 36. 二叉搜索树与双向链表
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。



为了让您更好地理解问题，以下面的二叉搜索树为例：







我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。







特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。



注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

注意：此题对比原题有改动。

通过次数134,490提交次数205,828
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二叉搜索树与双向链表_36 {

    @Test
    public void test() throws Exception {

    }

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

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;
}
