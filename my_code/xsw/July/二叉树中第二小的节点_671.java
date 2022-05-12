package xsw.July;
/*
671. 二叉树中第二小的节点
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

更正式地说，root.val = min(root.left.val, root.right.val) 总成立。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。



示例 1：


输入：root = [2,2,5,null,null,5,7]
输出：5
解释：最小的值是 2 ，第二小的值是 5 。
示例 2：


输入：root = [2,2,2]
输出：-1
解释：最小的值是 2, 但是不存在第二小的值。


提示：

树中节点数目在范围 [1, 25] 内
1 <= Node.val <= 231 - 1
对于树中每个节点 root.val == min(root.left.val, root.right.val)
通过次数30,308提交次数64,952
 */

import xsw.Nov_Dec.TreeNode;

import java.util.*;

public class 二叉树中第二小的节点_671 {

    /*执行用时：1 ms, 在所有 Java 提交中击败了18.43%的用户*/
    static PriorityQueue<Integer> values;

    public static int findSecondMinimumValue1(TreeNode root) {
        values = new PriorityQueue<Integer>();
        bfs1(root);
        System.out.println(values);
        values.poll();
        return values.size() > 0 ? values.poll() : -1;
    }

    private static int bfs1(TreeNode root) {
        if (root.right == null) {
            if (!values.contains((root.val)))
                values.add(root.val);
            return root.val;
        }
        int value = Math.min(bfs1(root.left), bfs1(root.right));
        if (!values.contains((value)))
            values.add(value);
        return Math.min(root.left.val, root.right.val);
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.stringToTreeNode("2,2,2147483647");
        System.out.println(findSecondMinimumValue1(node));
    }

    static class Solution {
        /*
        思路:
            根据题目中的描述「如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个」，
            我们可以知道，对于二叉树中的任意节点 x，x 的值不大于其所有子节点的值，因此：

            对于二叉树中的任意节点 x，x 的值不大于以 x 为根的子树中所有节点的值。

            令 x 为二叉树的根节点，此时我们可以得出结论：

            二叉树根节点的值即为所有节点中的最小值。
         */
        int ans;
        int rootvalue;

        public int findSecondMinimumValue(TreeNode root) {
            ans = -1;
            rootvalue = root.val;
            dfs(root);
            return ans;
        }

        public void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            if (ans != -1 && node.val >= ans) {
                return;
            }
            if (node.val > rootvalue) {
                ans = node.val;
            }
            dfs(node.left);
            dfs(node.right);
        }
    }
}