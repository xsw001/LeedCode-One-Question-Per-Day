//
// @lc app=leetcode.cn id=1398 lang=java
//
// [1398] number-of-ways-to-stay-in-the-same-place-after-some-steps
//
class Solution {
private static final int MOD = 1000000007;

    private static int arr;
    private static long[][] memo;

    public static int numWays(int steps, int arrLen) {
        // 数组长度最多就是一般的步数+1, 否则走太远就回不来了。
        arr = Math.min(arrLen, steps / 2 + 1);

        memo = new long[arr][steps + 1];
        for (int i = 0; i < arr; i++) {
            for (int j = 0; j < steps + 1; j++) {
                memo[i][j] = -1;
            }
        }
        return (int) backTrack(0, steps);
    }

    private static long backTrack(int from, int steps) {
        if (from == 0 && steps == 0) {
            return 1;
        }

        if (from > steps) {
            return 0;
        }

        if (memo[from][steps] != -1) {
            return memo[from][steps];
        }

        // 不动
        long nonMoveCount = backTrack(from, steps - 1);

        // 向左
        long leftMoveCount = 0;
        if (from > 0) {
            leftMoveCount = backTrack(from - 1, steps - 1);
        }

        // 向右
        long rightMoveCount = 0;
        if (from < arr - 1) {
            rightMoveCount = backTrack(from + 1, steps - 1);
        }

        memo[from][steps] = (nonMoveCount + leftMoveCount + rightMoveCount) % MOD;

        return memo[from][steps];
    }
}
// @lc code=end