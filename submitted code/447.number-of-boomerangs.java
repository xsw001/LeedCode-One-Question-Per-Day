//
// @lc app=leetcode.cn id=447 lang=java
//
// [447] number-of-boomerangs
//
class Solution {
    public static int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = distance(points[i], points[j]);
                dist[i][j] = distance;
                dist[j][i] = distance;
            }
        }
        int ans = 0;
        for (int[] doubles : dist) {
            ans += numBoomerangs(doubles);
        }
        return ans;
    }

    private static int numBoomerangs(int[] arr) {
        int asn = 0;
        HashMap<Double, Integer> map = new HashMap<>();
        for (double v : arr) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        for (Double key : map.keySet()) {
            Integer val = map.get(key);
            if (val > 1)
                asn += val * (val - 1);
        }
        return asn;
    }

    private static int distance(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}
// @lc code=end