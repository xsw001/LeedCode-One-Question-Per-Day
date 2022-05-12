//
// @lc app=leetcode.cn id=893 lang=java
//
// [893] all-nodes-distance-k-in-binary-tree
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        bfs(root, map);
        ArrayList<Integer> ans = new ArrayList<>();
        int start = target.val;
        if(k == 0) {
            ans.add(start);
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
// @lc code=end