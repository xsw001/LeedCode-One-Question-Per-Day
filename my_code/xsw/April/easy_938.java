package xsw.April;
/*
938. 二叉搜索树的范围和
给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。



示例 1：


输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
输出：32
示例 2：


输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
输出：23


提示：

树中节点数目在范围 [1, 2 * 104] 内
1 <= Node.val <= 105
1 <= low <= high <= 105
所有 Node.val 互不相同
通过次数52,811提交次数66,864
 */

import xsw.Nov_Dec.TreeNode;

import java.util.ArrayList;

public class easy_938 {

    static int ans;

    public static int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }

    private static void dfs(TreeNode root, int low, int high) {
        if (root == null)
            return;
        if (root.val >= low)
            dfs(root.left, low, high);
        if (root.val >= low && root.val <= high) {
            ans += root.val;
        }
        if (root.val <= high)
            dfs(root.right, low, high);
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
        ArrayList<String> list = new ArrayList<>();
        TreeNode node = TreeNode.stringToTreeNode("18,9,27,6,15,24,30,3,null,12,null,21");
        System.out.println(rangeSumBST(node, 18, 24));
    }

}