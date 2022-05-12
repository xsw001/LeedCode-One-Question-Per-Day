//
// @lc app=leetcode.cn id=1983 lang=java
//
// [1983] maximum-population-year
//
class Solution {
    public int maximumPopulation(int[][] logs) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] log : logs) {
            for (int i = log[0]; i < log[1]; i++) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        PriorityQueue<Integer> list = new PriorityQueue<Integer>(map.keySet());
        int ans = 0, max = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Integer key = list.poll();
            Integer num = map.get(key);
            if (num > max) {
                ans = key;
                max = num;
            }
        }
        return ans;
    }
}
// @lc code=end