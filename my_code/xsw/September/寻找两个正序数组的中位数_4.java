package xsw.September;
/*
4. 寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。



示例 1：

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
示例 2：

输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
示例 3：

输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000
示例 4：

输入：nums1 = [], nums2 = [1]
输出：1.00000
示例 5：

输入：nums1 = [2], nums2 = []
输出：2.00000


提示：

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106


进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

通过次数493,105提交次数1,209,933
 */

import java.util.ArrayList;

public class 寻找两个正序数组的中位数_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0, k = 0;
        int mid = (m + n) / 2;
        int a = 0, b = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                a = b;
                b = nums1[i++];
            } else {
                a = b;
                b = nums2[j++];
            }
            if (k == mid)
                return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
            ++k;
        }
        while (i < m) {
            a = b;
            b = nums1[i++];
            if (k == mid)
                return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
            ++k;
        }
        while (j < n) {
            a = b;
            b = nums2[j++];
            if (k == mid)
                return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
            ++k;
        }
        return (m + n) % 2 == 0 ? (a + b) / 2.0 : b;
    }

    public static void main(String[] args) {
        int[] data = {1, 8};
        int[] data1 = {4};
        System.out.println(findMedianSortedArrays(data, data1));
        System.out.println(findMedianSortedArrays1(data, data1));
    }


    // 第 1 个数组分割线左边的第 1 个数小于等于第 2 个数组分割线右边的第 1 的数；
    // 第 1 个数组分割线右边的第 1 的数 大 于 第 2 个数组分割线左边的第 1 个数。

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 始终保证nums1为较短的数组，nums1过长可能导致j为负数而越界
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        // m+n 为奇数，分割线要求左侧有 (m+n)/2 + 1 个元素
        // m+n 为偶数，分割线要求左侧有 (m+n)/2     个元素
        // 两种情况其实可以统一写作 (m+n+1)/2，表示对(m+n)/2向上取整
        // 对整数来说，向上取整等于：(被除数 + (除数 - 1)) / 除数
        // 也可以使用Math类中提供的库函数
        int leftTotal = (m + n + 1) / 2;
        int left = 0, right = m;
        while (left < right) {
            // 分割线左边的元素总数是固定的，我们 只要能确定 1 个数组上元素的位置，另一个位置自然就可以确定下来
            // +1 向上取整避免 left + 1 = right 时可能无法继续缩小区间而陷入死循环
            int i = (right + left) / 2 + 1;
            int j = leftTotal - i;

            //要找最大i，使得nums1[i-1] <= nums2[j]
            //使用对立面缩小区间
            if (nums1[i - 1] > nums2[j]) {
                // [i+1, m]均不满足
                right = i - 1;
            } else {
                // i满足说明[0, i-1]均不为满足条件的最大i，舍去以缩小区间
                left = i;
            }
        }

        //退出循环时left=right，表示最终nums1中分割线的位置
        int i = left;
        //nums2中分割线的位置
        int j = leftTotal - left;
        //System.out.println(i);

        //判断极端情况
        int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];  //nums1分割线左边没有元素
        int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];  //nums2分割线左边没有元素
        int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];     //nums1分割线右边没有元素
        int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];     //nums2分割线右边没有元素

        // 若m+n为奇数，分割线左侧的最大值即为中位数；若为偶数，分割线左侧的最大值与分割线右侧的最小值的平均数即为中位数
        if ((m + n) % 2 == 0) {
            return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
        } else {
            return Math.max(nums1LeftMax, nums2LeftMax);
        }
    }
}