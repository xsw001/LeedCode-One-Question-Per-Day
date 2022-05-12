package xsw.April;
/*
897. 递增顺序搜索树
给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。



示例 1：


输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
示例 2：


输入：root = [5,1,7]
输出：[1,null,5,null,7]


提示：

树中节点数的取值范围是 [1, 100]
0 <= Node.val <= 1000
通过次数34,169提交次数46,161
 */

import xsw.Nov_Dec.TreeNode;

import java.util.ArrayList;

public class easy_897 {

    public static TreeNode increasingBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        TreeNode res = new TreeNode();
        TreeNode temp = res;
        help(root,list);
        for (Integer value : list) {
            TreeNode node = new TreeNode(value);
            temp.left = null;
            temp.right = node;
            temp = node;
        }
        return res.right;
    }

    public static void help(TreeNode root,ArrayList<Integer> list) {
        if (root == null)
            return;
        help(root.left,list);
        list.add(root.val);
        help(root.right,list);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.stringToTreeNode("5,3,6,2,4,null,8,1,null,null,null,7,9");
        System.out.println(increasingBST(root));
    }

    private TreeNode resNode;

    public TreeNode increasingBST2(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }
}