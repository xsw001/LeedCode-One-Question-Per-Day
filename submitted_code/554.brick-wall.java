//
// @lc app=leetcode.cn id=554 lang=java
//
// [554] brick-wall
//
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (List<Integer> widths : wall) {
            int key = 0;
            for (int i = 0; i < widths.size() - 1; i++) {
                key += widths.get(i);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        int res = wall.size();
        for (Integer key : map.keySet()) {
            res = Math.min(res, wall.size() - map.get(key));
        }
        return res;
    }
}
// @lc code=end