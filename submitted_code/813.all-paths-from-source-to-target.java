//
// @lc app=leetcode.cn id=813 lang=java
//
// [813] all-paths-from-source-to-target
//
class Solution {
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        back(ans, list, graph, 0);
        return ans;
    }

    private static void back(ArrayList<List<Integer>> ans, ArrayList<Integer> list, int[][] graph, int i) {
        if (i == graph.length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int j = 0; j < graph[i].length; j++) {
            list.add(graph[i][j]);
            back(ans, list, graph, graph[i][j]);
            list.remove(list.size() - 1);
        }
    }
}
// @lc code=end