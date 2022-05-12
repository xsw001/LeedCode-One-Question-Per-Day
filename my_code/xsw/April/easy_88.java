package xsw.April;
/*
88. 合并两个有序数组
给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。



示例 1：

输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
输出：[1,2,2,3,5,6]
示例 2：

输入：nums1 = [1], m = 1, nums2 = [], n = 0
输出：[1]


提示：

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[i] <= 109
通过次数307,766提交次数616,232
 */

import java.util.Arrays;

public class easy_88 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
        } else {
            int end = m + n - 1;
            --m;
            for (int i = n - 1; i >= 0; ) {
                nums1[end--] = nums1[m] > nums2[i] ? nums1[m--] : nums2[i--];
                if(m < 0){
                    for (int j = i; j >= 0; j--){
                        nums1[end--] = nums2[j];
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {4,0, 0, 0,0,0};
        int[] nums2 = {1, 2, 3,5,6};
        merge(nums1, 1, nums2, 5);
        System.out.println(Arrays.toString(nums1));
    }

}