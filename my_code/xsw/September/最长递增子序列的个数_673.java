package xsw.September;
/*
673. 最长递增子序列的个数
给定一个未排序的整数数组，找到最长递增子序列的个数。

示例 1:

输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
示例 2:

输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

通过次数30,638提交次数77,158
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 最长递增子序列的个数_673 {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int max = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {// 可以接在后边
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; // 原来有几个最长的，再接一个还是几个最长的
                    } else if (dp[j] + 1 == dp[i]) {// 接在后边，也是最长的，那就加起来
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                ans = cnt[i];
            } else if (dp[i] == max)
                ans += cnt[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {2, 2, 2, 2, 2};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(findNumberOfLIS(data));
    }

    static class Solution {
        public int findNumberOfLIS(int[] nums) {
            int n = nums.length;
            int[] f = new int[n], g = new int[n];
            int max = 1;
            for (int i = 0; i < n; i++) {
                f[i] = g[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        if (f[i] < f[j] + 1) {
                            f[i] = f[j] + 1;
                            g[i] = g[j];
                        } else if (f[i] == f[j] + 1) {
                            g[i] += g[j];
                        }
                    }
                }
                max = Math.max(max, f[i]);
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (f[i] == max) ans += g[i];
            }
            return ans;
        }
    }
/*
    作者：AC_OIer
    链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/gong-shui-san-xie-lis-de-fang-an-shu-wen-obuz/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}