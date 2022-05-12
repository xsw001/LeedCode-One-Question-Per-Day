//
// @lc app=leetcode.cn id=1435 lang=java
//
// [1435] xor-queries-of-a-subarray
//
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        int[] pre = new int[arr.length];
        pre[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre[i] = pre[i - 1] ^ arr[i];
        }
        // System.out.println(Arrays.toString(pre));
        // for (int i = 0; i < arr.length; i++) {
        //     int temp = 0;
        //     for (int j = 0; j <= i; j++) {
        //         temp ^= arr[j];
        //     }
        //     System.out.print(temp);
        // }
        // System.out.println();
        int i = 0;
        for (int[] query : queries) {
            //System.out.println(query[1] + " " + query[0]);
            //System.out.println(pre[query[1]] + " " + pre[query[0]]);
            res[i++] = pre[query[1]] ^ (query[0] == 0 ? 0 : pre[query[0] - 1]);
        }
        return res;
    }
}
// @lc code=end