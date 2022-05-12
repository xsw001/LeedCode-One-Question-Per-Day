package xsw.October;
/*

 */

import xsw.Nov_Dec.TreeNode;

import java.util.*;

public class 二叉搜索树中第K小的元素_230 {

    static ArrayList<Integer> list;
    static int n;

    public static int kthSmallest(TreeNode root, int k) {
        n = k;
        list = new ArrayList<Integer>();
        bianli(root);
        return list.get(k - 1);
    }

    private static void bianli(TreeNode root) {
        if (root == null)
            return;
        if(list.size() < n) {
            bianli(root.left);
            list.add(root.val);
            bianli(root.right);
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        TreeNode node = TreeNode.stringToTreeNode("3,1,4,null,2");
        System.out.println(kthSmallest(node,2));
    }
    class Solution {
        public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                --k;
                if (k == 0) {
                    break;
                }
                root = root.right;
            }
            return root.val;
        }
    }
/*
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}