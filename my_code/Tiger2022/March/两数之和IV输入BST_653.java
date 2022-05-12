package Tiger2022.March;
/*

 */

import org.junit.Test;
import xsw.Nov_Dec.TreeNode;

import java.util.HashSet;

public class 两数之和IV输入BST_653 {

    @Test
    public void test() throws Exception {
        TreeNode node = TreeNode.stringToTreeNode("1");
        System.out.println(findTarget(node, 2));
    }

    HashSet<Integer> set;
    boolean ans;

    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        ans = false;
        dfs(root, k);
        System.out.println(set);
        return ans;
    }

    public void dfs(TreeNode root, int k) {
        if (ans || root == null)
            return;
        dfs(root.left, k);
        if (set.contains(k - root.val)) {
            ans = true;
            return;
        }
        set.add(root.val);
        dfs(root.right, k);
    }
}
