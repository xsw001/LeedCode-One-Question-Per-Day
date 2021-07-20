//
// @lc app=leetcode.cn id=1000221 lang=java
//
// [1000221] o8SXZn
//
class Solution {
    public static int storeWater(int[] bucket, int[] vat) {
        int maxVat = 0, ans = 100000, n = vat.length;
        for (int i : vat)
            maxVat = Math.max(maxVat, i);
        if (maxVat == 0)
            return 0;
        for (int i = 1; i <= maxVat; i++) {
            int res = i;
            for (int j = 0; j < n; j++) {
                res += Math.max(0, (vat[j] - 1) / i + 1 - bucket[j]);
            }
            //System.out.println(i + " " + res);
            ans = Math.min(ans, res);
        }
        return ans;
    }
}
// @lc code=end