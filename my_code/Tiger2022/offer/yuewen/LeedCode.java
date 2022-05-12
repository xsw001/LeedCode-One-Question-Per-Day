package Tiger2022.offer.yuewen;
/*

 */

import org.junit.Test;
import xsw.Nov_Dec.TreeNode;

import java.util.ArrayList;

public class LeedCode {

    @Test
    public void test() throws Exception {

    }

    public boolean isSubtree1(TreeNode s, TreeNode t) {
        if (s == null || t == null)
            return false;
        return isSub(s, t) || isSubtree1(s.left, t) || isSubtree1(s.right, t);
    }

    private boolean isSub(TreeNode s, TreeNode t) {
        if (t == null)
            return true;
        if (s == null || s.val != t.val)
            return false;
        return isSub(s.left, t.left) && isSub(s.right, t.right);
    }


    public boolean isSubtree(TreeNode s, TreeNode t) {
        ArrayList<TreeNode> list = new ArrayList<>();
        find(s, t.val, list);
        for (int i = 0; i < list.size(); i++) {
            if (isSub(s, t))
                return true;
        }
        return false;
    }

    private void find(TreeNode s, int val, ArrayList<TreeNode> list) {
        if (s == null)
            return;
        if (s.val == val)
            list.add(s);
        find(s.left, val, list);
        find(s.right, val, list);
    }
}
