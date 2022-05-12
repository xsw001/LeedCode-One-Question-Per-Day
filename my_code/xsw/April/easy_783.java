package xsw.April;
/*

 */

import xsw.Nov_Dec.TreeNode;

import java.util.*;

public class easy_783 {

    /*执行用时：0 ms, 在所有 Java 提交中击败了 100% 的用户
    内存消耗：36.3 MB, 在所有 Java 提交中击败了9.38%的用户*/
    public static int minDiffInBST1(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        dfs(root, list);
        System.out.println(list);
        int res = 10001;
        int a = list.get(0);
        int b = list.get(1);
        res = Math.min(res, b - a);
        for (int i = 2; i < list.size(); i++) {
            a = b;
            b = list.get(i);
            res = Math.min(res, b - a);
        }
        return res;
    }

    private static void dfs(TreeNode root, ArrayList<Integer> list) {
        if (root == null)
            return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    public static void main(String[] args) {
        String s = "27,null,34,null,58,50,null,44";
        TreeNode node = TreeNode.stringToTreeNode(s);
        System.out.println(minDiffInBST(node));
    }

    public static int minDiffInBST(TreeNode root) {
        //树非空
        int res = 10001;
        int last = -10001;
        TreeNode p = root;
        Deque<TreeNode> s = new ArrayDeque<>();
        while (!s.isEmpty() || p != null) {
            //一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while (p != null) {
                s.push(p);
                p = p.left;
            }
            //当p为空时，说明已经到达左子树最下边，这时需要出栈了
            if (!s.isEmpty()) {
                p = s.pop();
                res = Math.min(res, p.val-last);
                last = p.val;
                //进入右子树，开始新的一轮左子树遍历(这是递归的自我实现)
                p = p.right;
            }
        }
        return res;
    }
}