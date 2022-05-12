package xsw.Nov_Dec;/*
103. 二叉树的锯齿形层序遍历
给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层序遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
*/

import java.util.*;

public class LeedCode103 {
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> list = new ArrayDeque<>();
        list.add(root);
        int flag = 0;
        while (!list.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int len = list.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = list.poll();
                level.add(node.val);
                if (node.left != null)
                    list.add(node.left);
                if (node.right != null)
                    list.add(node.right);
            }
            ++flag;
            if (flag % 2 == 0) {
                Collections.reverse(level);
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        String node = "3,9,20,null,null,15,7";
        TreeNode root = TreeNode.stringToTreeNode(node);
        List<List<Integer>> order = zigzagLevelOrder(root);
        System.out.println(order);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            //为了满足题目要求的返回值为「先从左往右，再从右往左」交替输出的锯齿形
            //我们可以利用「双端队列」的数据结构来维护当前层节点值输出的顺序。
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }
}
