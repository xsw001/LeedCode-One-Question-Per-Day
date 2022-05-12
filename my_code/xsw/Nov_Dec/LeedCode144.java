package xsw.Nov_Dec;/*
144. 二叉树的前序遍历

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,2,3]
*/

import java.util.ArrayList;
import java.util.List;

public class LeedCode144 {

    /*
    Morris遍历的过程：

    设当前节点为 cur，初始 cur = head，则 cur 按照以下规则移动：

    1.若 cur == null，则过程停止，否则继续下面的过程；
    2.若 cur 无左子树，则令 cur = cur.right；
    3.若 cur 有左子树，则找到 cur 左子树上最右的节点，记作 mostRight：
    1) 若 mostRight.right == null，则令 mostRight.right = cur，即让 mostRight 的 right 指针指向当前节点 cur，然后令 cur = cur.left；
    2) 若 mostRight.right == cur， 则令 mostRight.right = null，即让 mostRight 的 right 指针指向空，然后令 cur = cur.right。
    */
    public static void preOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) { // 过程出口
            mostRight = cur.left;
            if (mostRight != null) { // cur有左子树
                // 找到cur左子树的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 如果mostRight.right == null，则让其指向cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left; // cur左移
                    continue; // 回到外层while循环
                } else { // mostRight == cur，则让其指向null
                    mostRight.right = null;
                }
            }
            // cur没有左子树或其左子树最右节点指向cur，均要将cur右移
            cur = cur.right;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode[] stack = new TreeNode[100];
        int top=-1;
        TreeNode p = root;
        while (p!=null || top!=-1)
        {
            if (p!=null) {
                stack[++top]=p;
                result.add(p.val);
                p = p.left;
            }
            else
            {
                p=stack[top--];
                p = p.right;
            }
        }
        return result;
    }
}
