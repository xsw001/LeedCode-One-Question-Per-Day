package xsw.July;
/*
987. 二叉树的垂序遍历
给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。

对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。

二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。

返回二叉树的 垂序遍历 序列。



示例 1：


输入：root = [3,9,20,null,null,15,7]
输出：[[9],[3,15],[20],[7]]
解释：
列 -1 ：只有结点 9 在此列中。
列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
列  1 ：只有结点 20 在此列中。
列  2 ：只有结点 7 在此列中。
示例 2：


输入：root = [1,2,3,4,5,6,7]
输出：[[4],[2],[1,5,6],[3],[7]]
解释：
列 -2 ：只有结点 4 在此列中。
列 -1 ：只有结点 2 在此列中。
列  0 ：结点 1 、5 和 6 都在此列中。
          1 在上面，所以它出现在前面。
          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
列  1 ：只有结点 3 在此列中。
列  2 ：只有结点 7 在此列中。
示例 3：


输入：root = [1,2,3,4,6,5,7]
输出：[[4],[2],[1,5,6],[3],[7]]
解释：
这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。


提示：

树中结点数目总数在范围 [1, 1000] 内
0 <= Node.val <= 1000
 */

import org.w3c.dom.ls.LSOutput;
import xsw.Nov_Dec.TreeNode;

import java.util.*;

public class 二叉树的垂序遍历_987 {

    static int layer,min;

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        int l = 0, r = 0;
        layer = 0;
        compute(root, l, r, map);
        List<List<Integer>> ans = new ArrayList<>();
        l = -min;
        r = min;
        while (!map.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = l;
            while (temp <= layer) {
                String k = temp + "#" + r;
                if (map.containsKey(k)) {
                    ArrayList<Integer> re = map.remove(k);
                    Collections.sort(re); // 节点值从小到大
                    list.addAll(re);
                }
                temp += 2; // “行号从小到大”
            }
            if (list.size() > 0) {
                ans.add(list);
            }
            l = r < 0 ? l - 1 : l + 1;
            ++r; // “列号从小到大”
        }
        return ans;
    }

    private static void compute(TreeNode root, int l, int r, HashMap<String, ArrayList<Integer>> map) {
        if (root == null)
            return;
        layer = Math.max(layer, l);
        min = Math.min(min, r);
        String key = l + "#" + r;
        int value = root.val;
        map.computeIfAbsent(key, v -> new ArrayList<Integer>()).add(value);
        compute(root.left, l + 1, r - 1, map);
        compute(root.right, l + 1, r + 1, map);
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.stringToTreeNode("0,1,null,null,2,6,3,null,null,null,4,null,5");
        System.out.println(verticalTraversal(node));
        TreeNode node1 = TreeNode.stringToTreeNode("0,2,1,3,null,5,22,9,4,12,25,null,null,13,14,8,6,null,null,null,null,null,27,24,26,null,17,7,null,28,null,null,null,null,null,19,null,11,10,null,null,null,23,16,15,20,18,null,null,null,null,null,21,null,null,29");
        System.out.println(verticalTraversal(node1));
    }

    class Solution { // 确实强 ！！！！！
        /*
        我们可以从根节点开始，对整棵树进行一次遍历，在遍历的过程中使用数组 nodes 记录下每个节点的行号 row，列号 col 以及值 value。
        在遍历完成后，我们按照 col 为第一关键字升序，row 为第二关键字升序，value 为第三关键字升序，对所有的节点进行排序即可
         */
        // 「“列号从小到大”，对于同列节点，“行号从小到大”，对于同列同行元素，“节点值从小到大”」
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<int[]> nodes = new ArrayList<int[]>();
            dfs(root, 0, 0, nodes);
            nodes.sort((tuple1, tuple2) -> {
                if (tuple1[0] != tuple2[0]) { // 排序绝了，把题目的意思完美诠释
                    return tuple1[0] - tuple2[0];
                } else if (tuple1[1] != tuple2[1]) {
                    return tuple1[1] - tuple2[1];
                } else {
                    return tuple1[2] - tuple2[2];
                }
            });
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            int size = 0;
            int lastcol = Integer.MIN_VALUE;
            for (int[] tuple : nodes) {
                int col = tuple[0], row = tuple[1], value = tuple[2];
                if (col != lastcol) {
                    lastcol = col;
                    ans.add(new ArrayList<Integer>());
                    size++;
                }
                ans.get(size - 1).add(value);// 小技巧！！！！！！！！
            }
            return ans;
        }

        public void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
            if (node == null) {
                return;
            }
            nodes.add(new int[]{col, row, node.val});
            dfs(node.left, row + 1, col - 1, nodes);
            dfs(node.right, row + 1, col + 1, nodes);
        }
    }

//    作者：LeetCode-Solution 链接：https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/solution/er-cha-shu-de-chui-xu-bian-li-by-leetcod-clsh/来源：力扣（LeetCode）著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    // 也可以用 列 做key， 《行， 值》做value，然后取的时候直接对value排序

}