package xsw.April;
/*
53. 最大子序和
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。



示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [0]
输出：0
示例 4：

输入：nums = [-1]
输出：-1
示例 5：

输入：nums = [-100000]
输出：-100000


提示：

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105


进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */

import java.util.ArrayList;

public class easy_53 {

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int l = nums.length, max = nums[0], pre = nums[0];
        for (int i = 1; i < l; i++) {
            if(pre > 0)
                pre += nums[i];
            else
                pre = nums[i];
            max = Math.max(max,pre);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(maxSubArray(data));

    }

}