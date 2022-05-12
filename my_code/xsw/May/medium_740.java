package xsw.May;
/*
740. 删除并获得点数
给你一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。



示例 1：

输入：nums = [3,4,2]
输出：6
解释：
删除 4 获得 4 个点数，因此 3 也被删除。
之后，删除 2 获得 2 个点数。总共获得 6 个点数。
示例 2：

输入：nums = [2,2,3,3,3,4]
输出：9
解释：
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。


提示：

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104
通过次数31,079提交次数49,843
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class medium_740 {

    public static int deleteAndEarn1(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums[nums.length - 1] + 1];
        for (int i : nums) {
            dp[i] += i;
            if (i > 2)
                dp[i] += dp[i - 2];
        }
        int res = 0;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(deleteAndEarn(data));
    }

    public static int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums[nums.length - 1] + 1];
        for (int i : nums) {
            dp[i] += i;
        }
        //198题
        int size = dp.length;
        int first = dp[0], second = Math.max(dp[0], dp[1]);
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + dp[i], second);
            first = temp;
        }
        return second;
    }
}