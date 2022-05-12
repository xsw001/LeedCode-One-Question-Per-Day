package xsw.April;
/*
368. 最大整除子集
给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
answer[i] % answer[j] == 0 ，或
answer[j] % answer[i] == 0
如果存在多个有效解子集，返回其中任何一个均可。



示例 1：

输入：nums = [1,2,3]
输出：[1,2]
解释：[1,3] 也会被视为正确答案。
示例 2：

输入：nums = [1,2,4,8]
输出：[1,2,4,8]


提示：

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
nums 中的所有整数 互不相同
通过次数26,868提交次数61,788
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeedCode368 {

    public static List<Integer> largestDivisible(int[] nums) {
        int len = nums.length;
        ArrayList<Integer> res = new ArrayList<>();
        res.add(nums[0]);
        for (int i = 1; i < len; i++) {
            boolean flag = true;
            for (Integer num : res) {
                if (num % nums[i] != 0 || nums[i] % num != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                res.add(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {5, 6, 12, 24};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(largestDivisibleSubset((data)));
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        Arrays.sort(nums);
        int max = nums[0], maxSize = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    max = nums[i];
                }
            }
        }
        //miao a
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && max % nums[i] == 0) {
                res.add(nums[i]);
                max = nums[i];
                --maxSize;
            }
        }
        return res;
    }
}