package Tiger2022.offer;
/*
剑指 Offer 34. 二叉树中和为某一值的路径
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。



示例 1：



输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
示例 2：



输入：root = [1,2,3], targetSum = 5
输出：[]
示例 3：

输入：root = [1,2], targetSum = 0
输出：[]


提示：

树中节点总数在范围 [0, 5000] 内
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/

通过次数169,409提交次数290,393
 */

import org.junit.Test;
import xsw.Nov_Dec.TreeNode;

import java.util.*;

public class 二叉树中和为某一值的路径_34 {

    @Test
    public void test() throws Exception {
        TreeNode node = TreeNode.stringToTreeNode("5,4,8,11,null,13,4,7,2,null,null,5,1");
        System.out.println(pathSum(node,22));
    }

    List<List<Integer>> ans;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ans = new ArrayList<>();
        dfs(root, target, new ArrayList<Integer>());
        return ans;
    }

    public void dfs(TreeNode root, int target, ArrayList<Integer> list) {
        if (root == null)
            return;
        int val = root.val;
        list.add(val);
        if (target == val && root.left == null && root.right == null)
            ans.add(new ArrayList<>(list));
        dfs(root.left, target - val, list);
        dfs(root.right, target - val, list);
        list.remove(list.size() - 1);
    }

}
