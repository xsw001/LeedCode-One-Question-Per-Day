//
// @lc app=leetcode.cn id=600 lang=java
//
// [600] non-negative-integers-without-consecutive-ones
//
class Solution {
    public int findIntegers(int n) {
        int[] dp = new int[31];//dp[t] 表示高度为 t、根结点为 0 的满二叉树中，不包含连续 1 的从根结点到叶结点的路径数量
        dp[0] = dp[1] = 1;
        //高度为 t、根结点为 0 的满二叉树中不包含连续 1 的从根结点到叶结点的路径数量
        // 等于高度为 t-1、根结点为 0 的满二叉树中的路径数量与高度为 t−2，根结点为 0 的满二叉树中的路径数量之和
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        /*
    如果该子树有右子树，说明其左子树是满二叉树。而我们已经求出了满二叉树中的路径数量，直接考虑其右子树即可。
    同理，若该子树没有右子树，我们递归考虑其左子树即可。
         */
        // pre 记录上一层的根节点值，res 记录最终路径数
        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {// 当遍历到 i = 2 时，便是判断是否有高度为 2 的右子树
            int val = 1 << i;
            // if 语句判断 当前子树是否有右子树
            if ((n & val) != 0) {
                // 有右子树
                n -= val; //直接考虑其右子树即可。
                res += dp[i + 1]; // 先将左子树（满二叉树）的路径加到结果中

                // 处理右子树
                if (pre == 1) {
                    // 上一层为 1，之后要处理的右子树根节点肯定也为 1
                    // 此时连续两个 1，不满足题意，直接退出
                    break;
                }
                // 标记当前根节点为 1
                pre = 1;
            } else {
                // 无右子树，此时不能保证左子树是否为满二叉树，所以要在下一层再继续判断
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }
        return res;
    }
}
// @lc code=end