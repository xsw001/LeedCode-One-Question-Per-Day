package xsw.May;
/*
993. 二叉树的堂兄弟节点
在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。



示例 1：


输入：root = [1,2,3,4], x = 4, y = 3
输出：false
示例 2：


输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
输出：true
示例 3：



输入：root = [1,2,3,null,4], x = 2, y = 3
输出：false


提示：

二叉树的节点数介于 2 到 100 之间。
每个节点的值都是唯一的、范围为 1 到 100 的整数。


通过次数21,557提交次数40,590
 */

import xsw.Nov_Dec.TreeNode;

import java.util.ArrayList;

public class easy_993 {

    public static boolean isCousins(TreeNode root, int x, int y) {
        if (dfs(root, x) == dfs(root, y))
            return false;
        return deep(root, x, 0) == deep(root, y, 0);
    }

    public static TreeNode dfs(TreeNode root, int n) {
        if (root == null) return null;
        if (root.left != null && root.left.val == n) {
            return root;
        }
        if (root.right != null && root.right.val == n) {
            return root;
        }
        TreeNode left = dfs(root.left, n);
        TreeNode right = dfs(root.right, n);

        return left == null ? right : left;
    }

    public static int deep(TreeNode root, int node, int h) {
        if (root == null)
            return 0;
        if (root.val == node) {
            return h;
        }
        int a = deep(root.right, node, h + 1);
        int b = deep(root.left, node, h + 1);
        return Math.max(a, b);
    }

    public static void main(String[] args) {
        String str = "1,2,null,4";
        ArrayList<String> list = new ArrayList<>();
        TreeNode root = TreeNode.stringToTreeNode(str);
        System.out.println(isCousins(root, 4, 3));

    }

}