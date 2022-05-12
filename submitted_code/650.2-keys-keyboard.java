//
// @lc app=leetcode.cn id=650 lang=java
//
// [650] 2-keys-keyboard
//
class Solution {
    int ans;
    public int minSteps(int n) {
        if(n == 1)
            return 0;
        ans = n;
        back(n, 1, 1, 1,false);
        return ans;
    }

    private void back(int n, int count, int step, int c, boolean lastCopy) {
        if (count == n) {
            ans = Math.min(ans, step);
            return;
        }
        if (count > n || step > ans)
            return;
        if (!lastCopy)
            back(n, count, step + 1, count, true);
        back(n, count + c, step + 1, c, false);
    }
}
// @lc code=end