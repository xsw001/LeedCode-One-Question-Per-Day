package xsw.August;
/*
413. 等差数列划分
如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。

子数组 是数组中的一个连续序列。



示例 1：

输入：nums = [1,2,3,4]
输出：3
解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
示例 2：

输入：nums = [1]
输出：0


提示：

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000
 */

import java.util.*;

public class 等差数列划分_413 {

    // O(n^2)
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return 0;
        int ans = 0;
        int a = 0, b = 1, c = 2;
        while (c < n) {
            if (isArithmeticSlices(nums[a], nums[b], nums[c])) {
                ++ans;
                int diff = nums[c] - nums[b];
                while (c < n - 1 && nums[c + 1] - nums[c] == diff) {
                    ++c;
                    ++ans;
                }
            }
            ++a;
            ++b;
            c = b + 1;
        }
        return ans;
    }

    private static boolean isArithmeticSlices(int a, int b, int c) {
        return c - b == b - a;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(numberOfArithmeticSlices(data));
        System.out.println(numberOfArithmeticSlices1(data));

    }
    // O(n)
    /*
如果我们已经求出了 nums[i−1] 以及 nums[i] 作为等差数列的最后两项时，答案增加的次数 t_i，那么能否快速地求出 t_i+1呢？
答案是可以的：
    如果 nums[i]−nums[i+1]=d，那么在这一轮遍历中，j 会遍历到与上一轮相同的位置，答案增加的次数相同
    并且额外多出了 nums[i−1],nums[i],nums[i+1] 这一个等差数列，因此有：
    t_{i+1} = t_i + 1

    如果 nums[i]−num[i+1]=d，那么 j 从初始值 i-1 开始就会直接退出遍历，答案不会增加，因此有：
    t_{i+1} = 0
     */
    public static int numberOfArithmeticSlices1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}