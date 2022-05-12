//
// @lc app=leetcode.cn id=803 lang=java
//
// [803] cheapest-flights-within-k-stops
//
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int[] flight : flights) {
            dp[flight[0]][flight[1]] = flight[2];
        }
        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{src, src, 0});
        int v = 0;
        HashSet<String> set = new HashSet<>();
        while (!list.isEmpty() && v <= k) {
            ++v;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int[] arr = list.poll();
                for (int[] flight : flights) {
                    if (flight[0] == arr[1]) {
                        if (dp[arr[0]][flight[1]] == -1)
                            dp[arr[0]][flight[1]] = arr[2] + flight[2];
                        else
                            dp[arr[0]][flight[1]] = Math.min(dp[arr[0]][flight[1]], arr[2] + flight[2]);
                        if (!set.contains(arr[0] + "to" + flight[1])) {
                            list.add(new int[]{arr[0], flight[1], dp[arr[0]][flight[1]]});
                            set.add(arr[0] + "to" + flight[1]);
                        }
                    }
                }
            }
        }
        return dp[src][dst];
    }
}
// @lc code=end