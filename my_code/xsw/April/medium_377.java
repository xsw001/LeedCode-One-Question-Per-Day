package xsw.April;
/*
377. 组合总和 Ⅳ
给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。

题目数据保证答案符合 32 位整数范围。



示例 1：

输入：nums = [1,2,3], target = 4
输出：7
解释：
所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
请注意，顺序不同的序列被视作不同的组合。
示例 2：

输入：nums = [9], target = 3
输出：0


提示：

1 <= nums.length <= 200
1 <= nums[i] <= 1000
nums 中的所有元素 互不相同
1 <= target <= 1000


进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？

通过次数46,264提交次数93,343
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class medium_377 {

    /*回溯。超时*/
    public static int ans = 0;

    public static int combinationSum4_1(int[] nums, int target) {
        ans = 0;
        Arrays.sort(nums);
        if (nums[0] > target)
            return 0;
        backward(new ArrayList<Integer>(), target, nums);
        return ans;
    }

    private static void backward(ArrayList<Integer> path, int target, int[] candidates) {
        if (0 > target)
            return;
        if (0 == target) {
            ++ans;
            return;
        }
        for (int candidate : candidates) {
            if (candidate > target)
                return;
            path.add(candidate);
            backward(path, target - candidate, candidates);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
    }

    //记忆化搜索
    private static int[] memo;

    private static int combinationSum4_2(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return search(nums, target);
    }

    private static int search(int[] nums, int target) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += search(nums, target - num);
            }
        }
        memo[target] = res;
        return res;
    }

    /*用 dp[x] 表示选取的元素之和等于 x 的方案数，目标是求 dp[target]。*/
/*  当 1 ≤ i ≤ target 时，如果存在一种排列
    其中的元素之和等于 i，则该排列的最后一个元素一定是数组 nums 中的一个元素
    假设该排列的最后一个元素是 num，则一定有 num ≤ i，对于元素之和等于 i − num 的每一种排列
    在最后添加 num 之后即可得到一个元素之和等于 i 的排列
    因此在计算 dp[i] 时,     应该计算所有的 dp[i−num] 之和。
*/
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
