//
// @lc app=leetcode.cn id=62 lang=java
//
// [62] unique-paths
//
class Solution {
    public static int uniquePaths(int m, int n) {
        return path(m - 1, n - 1, new HashMap<>());
    }

    public static int path(int column, int row, Map<String, Integer> map) {
        if (column == 0 || row == 0)
            return 1;
        String key = column + "*" + row;
        if (map.containsKey(key))
            return map.get(key);
        int c = path(column - 1, row,map);
        int r = path(column, row - 1,map);
        int totla = c + r;
        map.put(key, totla);
        return totla;
    }
}
// @lc code=end