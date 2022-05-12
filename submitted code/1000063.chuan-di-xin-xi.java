//
// @lc app=leetcode.cn id=1000063 lang=java
//
// [1000063] chuan-di-xin-xi
//
class Solution {
    int ans;
    public int numWays(int n, int[][] relation, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] ints : relation) {
            ArrayList<Integer> list = map.getOrDefault(ints[0], new ArrayList<Integer>());
            list.add(ints[1]);
            map.put(ints[0], list);
        }
        int step = 0;
        ans = 0;
        dfs(map, 0, step, n, k);
        return ans;
    }

    private void dfs(HashMap<Integer, ArrayList<Integer>> map, int index, int step, int n, int k) {
        if (step == k) {
            if (index == n - 1)
                ++ans;
            return;
        }
        if (map.containsKey(index)) {
            for (Integer i : map.get(index)) {
                dfs(map, i, step + 1, n, k);
            }
        }
    }
}
// @lc code=end