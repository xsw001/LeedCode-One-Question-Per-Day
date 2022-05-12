//
// @lc app=leetcode.cn id=802 lang=java
//
// [802] k-th-smallest-prime-fraction
//
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> list = new PriorityQueue<>((o1, o2) -> arr[o1[0]] * arr[o2[1]] - arr[o1[1]] * arr[o2[0]]);
        for (int i = 0; i < n; i++) {
            list.add(new int[]{0, i});
        }
        int[] ans = new int[2];
        for (int i = 0; i < k; ++i) {
            int[] t = list.poll();
            ans[0] = arr[t[0]];
            ans[1] = arr[t[1]];
            if (t[0] + 1 < t[1])
                list.add(new int[]{t[0] + 1, t[1]});
        }
        return ans;
    }
}
// @lc code=end