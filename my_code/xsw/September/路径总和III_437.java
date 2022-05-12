package xsw.September;
/*
437. 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。



示例 1：



输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
示例 2：

输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3


提示:

二叉树的节点个数的范围是 [0,1000]
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
通过次数101,940提交次数179,503
 */

import xsw.Nov_Dec.TreeNode;

import java.util.*;

public class 路径总和III_437 {

    static int ans;

    public static int pathSum(TreeNode root, int targetSum) {
        ans = 0;
        if (root != null)
            children(root, targetSum);
        return ans;
    }

    private static void children(TreeNode root, int targetSum) {
        self(root, targetSum);
        if (root.left != null)
            children(root.left, targetSum);
        if (root.right != null)
            children(root.right, targetSum);
    }

    private static void self(TreeNode root, int sum) {
        if (root == null)
            return;
        if (root.val == sum)
            ++ans;
        self(root.left, sum - root.val);
        self(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        TreeNode node = TreeNode.stringToTreeNode("0,1,1");
        System.out.println(pathSum(node, 1));

    }

    static class Solution {
        private int ans = 0;

        public int pathSum(TreeNode root, int targetSum) {
            Map<Integer, Integer> sum = new HashMap<Integer, Integer>();
            sum.put(0, 1);  // 初始化
            dfs(root, 0, sum, targetSum);
            return ans;
        }

        /**
         * pre: 到上一个节点的前缀和
         * sum：前缀和 -> 前缀和的count
         */
        public void dfs(TreeNode node, int pre, Map<Integer, Integer> sum, int targetSum) {
            if (null == node) return;
            pre += node.val;    // 当前前缀和
            ans += sum.getOrDefault(pre - targetSum, 0);  // 统计答案
            sum.put(pre, sum.getOrDefault(pre, 0) + 1);   // 哈希表加入当前前缀和
            dfs(node.left, pre, sum, targetSum);
            dfs(node.right, pre, sum, targetSum);
            sum.put(pre, sum.get(pre) - 1);   // 哈希表删除当前前缀和（复原）
        }
    }
}