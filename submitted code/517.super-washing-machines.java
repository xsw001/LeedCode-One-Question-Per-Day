//
// @lc app=leetcode.cn id=517 lang=java
//
// [517] super-washing-machines
//
class Solution {
    public int findMinMoves(int[] machines) {
        int tot = Arrays.stream(machines).sum();
        int n = machines.length;
        if (tot % n != 0) {
            return -1;
        }
        int avg = tot / n;
        int ans = 0, sum = 0;
        for (int num : machines) {
            num -= avg;
            sum += num;
            ans = Math.max(ans, Math.max(Math.abs(sum), num));
        }
        return ans;
    }
}

// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/super-washing-machines/solution/chao-ji-xi-yi-ji-by-leetcode-solution-yhej/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
// @lc code=end