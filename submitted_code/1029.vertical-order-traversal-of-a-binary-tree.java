//
// @lc app=leetcode.cn id=1029 lang=java
//
// [1029] vertical-order-traversal-of-a-binary-tree
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
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
}
// @lc code=end