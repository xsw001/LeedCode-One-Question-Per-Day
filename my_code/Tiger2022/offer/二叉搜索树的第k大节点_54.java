package Tiger2022.offer;
/*
剑指 Offer 54. 二叉搜索树的第k大节点
给定一棵二叉搜索树，请找出其中第 k 大的节点的值。



示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4


限制：

1 ≤ k ≤ 二叉搜索树元素个数
通过次数203,311提交次数266,908
 */

import org.junit.Test;
import xsw.Nov_Dec.TreeNode;

import java.io.OutputStream;
import java.util.*;

public class 二叉搜索树的第k大节点_54 {

    @Test
    public void test() throws Exception {
        TreeNode node = TreeNode.stringToTreeNode("3,1,4,null,2");
        System.out.println(kthLargest(node, 3));
        Scanner scanner = new Scanner(System.in);

    }

    int ans, k;

    public int kthLargest(TreeNode root, int k) {
        ans = 0;
        this.k = k-1;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null || ans != 0 || k < 0)
            return;

        if (root.right != null)
            dfs(root.right);
        if (k == 0) {
            ans = root.val;
        }
        --k;
        if (root.left != null)
            dfs(root.left);
    }
}
