package xsw.August;
/*
446. 等差数列划分 II - 子序列
给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。

如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。

例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
再例如，[1, 1, 2, 5, 7] 不是等差序列。
数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。

例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
题目数据保证答案是一个 32-bit 整数。



示例 1：

输入：nums = [2,4,6,8,10]
输出：7
解释：所有的等差子序列为：
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
示例 2：

输入：nums = [7,7,7,7,7]
输出：16
解释：数组中的任意子序列都是等差子序列。


提示：

1  <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
通过次数6,073提交次数13,528
 */

import java.util.*;

public class 等差数列划分II_子序列_446 {

    // 回溯，超时
    static int ans;

    public static int numberOfArithmeticSlices(int[] nums) {
        ArrayList<Long> list = new ArrayList<>();
        ans = 0;
        arithmeticSlices(nums, list, 0);
        return ans;
    }

    private static void arithmeticSlices(int[] nums, ArrayList<Long> list, int start) {
        if (list.size() > 2) {
            if (isArithmeticSlices(list))
                ++ans;
        }
        for (int i = start; i < nums.length; i++) {
            list.add((long) nums[i]);
            arithmeticSlices(nums, list, i + 1);
            list.remove(list.size() - 1);
        }

    }

    private static boolean isArithmeticSlices(ArrayList<Long> list) {
        long d = list.get(1) - list.get(0);
        for (int i = 2; i < list.size(); i++) {
            if (list.get(i) - list.get(i - 1) != d)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int[] data = {2, 4, 6, 8, 10, 8, 6, 4, 2};
        System.out.println(numberOfArithmeticSlices(data));
        System.out.println(numberOfArithmeticSlices2(data));
        System.out.println(numberOfArithmeticSlices1(data));

    }

    public static int numberOfArithmeticSlices1(int[] nums) {
        // 弱等差子序列的长度至少为2
        int ans = 0, n = nums.length;
        HashMap<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 首先计算nums[i] 和 nums[j] 之间的差值
                long d = (long) nums[i] - nums[j];
                // 获得以nums[j]为结尾，差值为d的弱等差子序列的个数
                int cnt = dp[j].getOrDefault(d, 0);
                // 所有以nums[j]为结尾，差值为d的弱等差子序列加上nums[i]后长度至少为3，一定是符合题意的一个等差子序列
                ans += cnt;
                // 以nums[i]结尾，差值为d的弱等差子序列的个数应该加上两部分
                //      一部分以nums[j]为结尾，差值为d的弱等差子序列的个数
                //      另一部分是nums[j], nums[i]这两个元素构成的弱等差子序列的个数 1
                dp[i].put(d, dp[i].getOrDefault(d, 0) + cnt + 1);
            }
        }
        return ans;
    }

    /*
    状态定义：dp[j][i]表示以[..,nums[j],nums[i]]为结尾的等差子序列的数量；
    状态转移：dp[j][i]=∑(dp[k][j]+1)，我们只要找到所有满足条件的k
            就可以组成..,[nums[k],nums[j],nums[i]]的子序列
            这里我们使用Map+List存储所有元素对应的下标，这样很方便就能找到这样的k了。
     */
    public static int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        // 记录所有元素的下标，有可能有重复元素，所以，使用list
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }

        int ans = 0;

        // dp[j][i]表示以[nums[j],nums[i]]结尾的子序列的等差子序列数量
        // 这里我们可以识别到只统计长度大于等于3的子序列
        int[][] dp = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 看能不能找到nums[k]使得 nums[i]-nums[j]=nums[j]-nums[k]
                // =====>  nums[k] = 2 * nums[j] - nums[i]
                long numsK = 2L * nums[j] - nums[i];
                if (numsK > Integer.MAX_VALUE || numsK < Integer.MIN_VALUE) {
                    continue;
                }

                // 能找到这样的k，说明可以缓存三元组，找不到说明不会组成三元组
                // 正好题目要求的也是最低长度为3
                if (map.containsKey((int)numsK)) {
                    List<Integer> list = map.get((int) numsK);
                    for (Integer k : list) {
                        if (k < j) {
                            // 如果有多个k，需要累加，比如[7,7,7,7,7]这种用例
                            dp[j][i] += dp[k][j] + 1;
                        }
                    }
                }

                ans += dp[j][i];
            }
        }

        return ans;

/*        作者：tong-zhu
        链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/solution/tong-ge-lai-shua-ti-la-yi-ti-liang-jie-d-404p/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}