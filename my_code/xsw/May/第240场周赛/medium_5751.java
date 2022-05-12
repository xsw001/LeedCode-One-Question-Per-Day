package xsw.May.第240场周赛;
/*
给你两个 非递增 的整数数组 nums1​​​​​​ 和 nums2​​​​​​ ，数组下标均 从 0 开始 计数。

下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​

返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。

一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。

 

示例 1：

输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
输出：2
解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
示例 2：

输入：nums1 = [2,2,2], nums2 = [10,10,1]
输出：1
解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
最大距离是 1 ，对应下标对 (0,1) 。
示例 3：

输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
输出：2
解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
示例 4：

输入：nums1 = [5,4], nums2 = [3,2]
输出：0
解释：不存在有效下标对，所以返回 0 。
 

提示：

1 <= nums1.length <= 105
1 <= nums2.length <= 105
1 <= nums1[i], nums2[j] <= 105
nums1 和 nums2 都是 非递增 数组
通过次数2,251提交次数7,355
 */

import org.w3c.dom.ls.LSOutput;

import java.util.*;

/*
 *                           我为啥没想到双指针呢
 * 我为啥没想到双指针呢
 * 我为啥没想到双指针呢
 *
 * */
public class medium_5751 {

    //cuo le
    public static int maxDistance1(int[] nums1, int[] nums2) {
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int j = i;
            while (nums1[i] <= nums2[j] && j < nums2.length - 1)//注意顺序，否则会月结
                j++;
            ans = Math.max(ans, j - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {555, 30, 5, 4, 2};
        int[] data1 = {100, 20, 10, 10, 5};
        System.out.println(maxDistance3(data, data1));
    }

    public static int maxDistance3(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (nums1[l1 - 1] > nums2[0])
            return 0;
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int k = nums1[i];
            int l = i, r = l2 - 1;
            while (l < r) {  //找的是： 第一个小于 k 的值
                int mid = (l + r) / 2;
                if (nums2[mid] >= k)
                    l = mid + 1;
                else
                    r = mid;
            }
            if(l < l2)
                if (nums2[l] < k)
                    --l;
            ans = Math.max(ans, l - i);
        }
        /*
        for (int i = 0; i < nums1.length; i++) {
            int k = nums1[i];
            int l = i, r = l2 - 1;
            while (l < r) {  //找的是： 大于等于 k 的值
                int mid = (l + r + 1) / 2;
                if (nums2[mid] >= k)
                    l = mid;
                else
                    r = mid-1;
            }
            ans = Math.max(ans, l - i);
        }
        */
        return ans;
    }

    public static int maxDistance2(int[] a, int[] b) {
        int best = 0;
        for (int i = 0, j = 0; i < a.length; i++) {
            j = Math.max(i, j);
            while (j < b.length - 1 && b[j + 1] >= a[i]) {
                j++;
            }
            best = Math.max(j - i, best);
        }
        return best;
    }

    public static int maxDistance(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (nums1[l1 - 1] > nums2[0])
            return 0;
        int ans = 0;
        int i = 0, j = 0;
        while (i < l1 && j < l2) {
            if (nums1[i] <= nums2[j]) {//牛逼啊，不用管，往前走就行了
                ans = Math.max(ans, j - i);
                ++j;
            } else
                ++i;
        }
        return ans;
    }
}