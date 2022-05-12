package xsw.January;
/*
697. 数组的度
给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。

你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。



示例 1：

输入：[1, 2, 2, 3, 1]
输出：2
解释：
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
示例 2：

输入：[1,2,2,3,1,4,2]
输出：6
 */

import java.util.ArrayList;

public class LeedCode679 {

    public static int findShortestSubArray(int[] nums) {
        int max = 0;
        for (int i : nums) {
            max = Math.max(max, i);
        }
        int[] count = new int[max + 1];
        for (int i : nums) {
            ++count[i];
        }
        int freq = 0;
        for (int i : count) {
            freq = Math.max(freq, i);
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < max + 1; i++) {
            if (count[i] == freq) {
                list.add(i);
            }
        }
        int res = 50000;
        for (Integer i : list) {
            res = Math.min(res,help(nums,i));
        }
        return res;
    }

    private static int help(int[] nums, Integer freq) {
        int left = 0, right = nums.length - 1;
        while (left < right && nums[left] != freq) {
            ++left;
        }
        while (left < right && nums[right] != freq) {
            --right;
        }
        return right - left + 1;
    }


    public static void main(String[] args) {
        int[] nums = {1,1,2,3,2};
        System.out.println(findShortestSubArray(nums));
    }
}