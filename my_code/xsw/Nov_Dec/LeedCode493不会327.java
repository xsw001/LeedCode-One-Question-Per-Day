package xsw.Nov_Dec;/*

493. 翻转对
给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

你需要返回给定数组中的重要翻转对的数量。

示例 1:

输入: [1,3,2,3,1]
输出: 2
示例 2:

输入: [2,4,3,5,1]
输出: 3
注意:

给定数组的长度不会超过50000。
输入数组中的所有数字都在32位整数的表示范围内。
*/


import java.util.Arrays;

public class LeedCode493不会327 {
    //更超时
    public static int reversePairs(int[] nums) {
        int pairs = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int[] copy = Arrays.copyOfRange(nums, i + 1, len);
            Arrays.sort(copy);
            for (int num : copy) {
                if (nums[i] - num <= num)
                    break;
                ++pairs;
            }
        }
        return pairs;
    }
    //超时
    public static int reversePairs1(int[] nums) {
        int pairs = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                int a = nums[i];
                int b = nums[j];
                if (a-b > b)
                    ++pairs;
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 1};
        int pairs = reversePairs(nums);
        System.out.println(pairs);
    }

    //与「327. 区间和的个数」非常相像。
    /*
        在归并排序的过程中，假设对于数组 nums[l..r] 而言，我们已经分别求出了子数组 nums[l..m] 与 nums[m+1..r] 的翻转对数目
        * 并已将两个子数组分别排好序，则 nums[l..r] 中的翻转对数目
        * 就等于两个子数组的翻转对数目之和，加上左右端点分别位于两个子数组的翻转对数目。

        我们可以为两个数组分别维护指针 i,j。对于任意给定的 ii 而言，我们不断地向右移动 j
        * 直到 nums[i]≤2⋅nums[j]。
        * 此时，意味着以 i 为左端点的翻转对数量为 j−m−1。
        * 随后，我们再将 i 向右移动一个单位，并用相同的方式计算以 i 为左端点的翻转对数量。
        * 不断重复这样的过程，就能够求出所有左右端点分别位于两个子数组的翻转对数目。
    * */
    public static int reversePairs2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    public static int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = reversePairsRecursive(nums, left, mid);
            int n2 = reversePairsRecursive(nums, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int j = mid + 1;
            while (i <= mid) {
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                    j++;
                }
                ret += j - mid - 1;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = nums[p2++];
                } else if (p2 > right) {
                    sorted[p++] = nums[p1++];
                } else {
                    if (nums[p1] < nums[p2]) {
                        sorted[p++] = nums[p1++];
                    } else {
                        sorted[p++] = nums[p2++];
                    }
                }
            }
            if (sorted.length >= 0)
                System.arraycopy(sorted, 0, nums, left, sorted.length);
            return ret;
        }
    }
}
