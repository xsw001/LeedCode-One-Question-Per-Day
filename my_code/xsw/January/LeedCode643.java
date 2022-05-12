package xsw.January;
/*
643. 子数组最大平均数 I
给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。



示例：

输入：[1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75


提示：

1 <= k <= n <= 30,000。
所给数据范围 [-10,000，10,000]。
 */

import java.util.ArrayList;
import java.util.Collections;

public class LeedCode643 {

    public static double findMaxAverage(int[] nums, int k) {
        int l = nums.length;
        double all = 0;
        for (int i = 0; i < k; ++i) {
            all += nums[i];
        }
        double result = all;
        for (int i = k; i < l; ++i) {
            all = all-nums[i-k]+nums[i];
            result = Math.max(result,all);
        }
        return result/k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(findMaxAverage(nums, k));
    }
}