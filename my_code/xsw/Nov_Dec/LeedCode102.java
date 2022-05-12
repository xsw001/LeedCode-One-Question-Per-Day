package xsw.Nov_Dec;/*
102. 二叉树的层序遍历
给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。



示例：
二叉树：[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层序遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeedCode102 {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) {
            return  result;
        }
        ArrayDeque<TreeNode> list = new ArrayDeque<>();
        list.add(root);
        while(!list.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int len = list.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = list.poll();
                level.add(node.val);
                if(node.left!=null)
                    list.add(node.left);
                if(node.right!=null)
                    list.add(node.right);
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        String node = "1,2,3,4,5";
        TreeNode root = TreeNode.stringToTreeNode(node);
        List<List<Integer>> order = levelOrder1(root);
        System.out.println(order);
    }

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        // 如果为空则直接返回
        if (root == null){
            return ans;
        }
        List<Integer> level = new ArrayList<>();// 层次数组
        LinkedList<TreeNode> queue = new LinkedList<>();// 辅助遍历的队列
        TreeNode dummyNode = new TreeNode(Integer.MIN_VALUE);// 分节符，用于区分层次结构
        queue.addLast(root);
        queue.addLast(dummyNode);// 根节点直接推入分界符
        // 当辅助队列不为空
        while (!queue.isEmpty()){
            // 从队列中取出头节点
            TreeNode node = queue.poll();
            // 如果当前节点是分界符
            if (node == dummyNode){
                // 说明这一层遍历完毕，将数组加入结果
                ans.add(level);
                // 创建新数组
                level = new ArrayList<>();
                // 此时下一层所有节点应该都进入了队列
                // 当队列非空插入分界符
                if (!queue.isEmpty()){
                    queue.addLast(dummyNode);
                }
            }else {
                // 未到分界符就不断加入数
                level.add(node.val);
                // 节点左右不为空则入队
                if (node.left!=null){
                    queue.addLast(node.left);
                }
                if (node.right!=null){
                    queue.addLast(node.right);
                }
            }

        }
        return ans;
    }
}
