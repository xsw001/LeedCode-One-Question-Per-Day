package xsw.July;
/*
863. 二叉树中所有距离为 K 的结点
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。



示例 1：

输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
输出：[7,4,1]
解释：
所求结点为与目标结点（值为 5）距离为 2 的结点，
值分别为 7，4，以及 1



注意，输入的 "root" 和 "target" 实际上是树上的结点。
上面的输入仅仅是对这些对象进行了序列化描述。


提示：

给定的树是非空的。
树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
目标结点 target 是树上的结点。
0 <= K <= 1000.
通过次数16,358提交次数29,096
 */

import xsw.Nov_Dec.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        bfs(root, map);
        System.out.println(map);
        ArrayList<Integer> ans = new ArrayList<>();
        int start = target.val;
        if(k == 0) {
            ans.add(k);
            return ans;
        }
        int step = 0;
        LinkedList<Integer> list = new LinkedList<>();
        list.add(start);
        boolean[] visited = new boolean[501];
        visited[start] = true;
        while (!list.isEmpty()) {
            ++step;
            if (step > k)
                return ans;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Integer poll = list.poll();
                for (Integer ii : map.getOrDefault(poll, new ArrayList<Integer>())) {
                    if (!visited[ii]) {
                        list.add(ii);
                        visited[ii] = true;
                        if (step == k)
                            ans.add(ii);
                    }
                }
            }

        }
        return ans;
    }

    private static void bfs(TreeNode root, HashMap<Integer, ArrayList<Integer>> map) {
        if (root == null)
            return;
        if (root.left != null) {
            map.computeIfAbsent(root.val, k -> new ArrayList<Integer>()).add(root.left.val);
            map.computeIfAbsent(root.left.val, k -> new ArrayList<Integer>()).add(root.val);
        }
        if (root.right != null) {
            map.computeIfAbsent(root.val, k -> new ArrayList<Integer>()).add(root.right.val);
            map.computeIfAbsent(root.right.val, k -> new ArrayList<Integer>()).add(root.val);
        }
        bfs(root.left, map);
        bfs(root.right, map);
    }
}

public class 二叉树中所有距离为k的结点_863 {

    public static void main(String[] args) {
        TreeNode node = TreeNode.stringToTreeNode("0,1,null,null,2,null,3,null,4");
        TreeNode t = TreeNode.stringToTreeNode("3,null,4");
        System.out.println(Solution.distanceK(node, t, 0));
    }

    class Solution1 {
        /*若将 target 当作树的根结点，我们就能从 arget 出发，使用深度优先搜索去寻找与 target 距离为 k的所有结点，即深度为 k 的所有结点。

由于输入的二叉树没有记录父结点，为此，我们从根结点 root 出发，使用深度优先搜索遍历整棵树，同时用一个哈希表记录每个结点的父结点。

然后从 target 出发，使用深度优先搜索遍历整棵树，除了搜索左右儿子外，还可以顺着父结点向上搜索。

代码实现时，由于每个结点值都是唯一的，哈希表的键可以用结点值代替。
此外，为避免在深度优先搜索时重复访问结点，递归时额外传入来源结点 from，在递归前比较目标结点是否与来源结点相同，不同的情况下才进行递归
*/
        Map<Integer, TreeNode> parents = new HashMap<Integer, TreeNode>();
        List<Integer> ans = new ArrayList<Integer>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // 从 root 出发 DFS，记录每个结点的父结点
            findParents(root);

            // 从 target 出发 DFS，寻找所有深度为 k 的结点
            findAns(target, null, 0, k);

            return ans;
        }

        public void findParents(TreeNode node) {
            if (node.left != null) {
                parents.put(node.left.val, node);
                findParents(node.left);
            }
            if (node.right != null) {
                parents.put(node.right.val, node);
                findParents(node.right);
            }
        }

        public void findAns(TreeNode node, TreeNode from, int depth, int k) {
            if (node == null) {
                return;
            }
            if (depth == k) {
                ans.add(node.val);
                return;
            }
            if (node.left != from) {
                findAns(node.left, node, depth + 1, k);
            }
            if (node.right != from) {
                findAns(node.right, node, depth + 1, k);
            }
            if (parents.get(node.val) != from) {
                findAns(parents.get(node.val), node, depth + 1, k);
            }
        }
    }

/*    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/solution/er-cha-shu-zhong-suo-you-ju-chi-wei-k-de-qbla/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
