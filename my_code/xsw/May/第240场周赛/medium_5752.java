package xsw.May.第240场周赛;
/*
5752. 子数组最小乘积的最大值
一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。

比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。

请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。

子数组 定义为一个数组的 连续 部分。



示例 1：

输入：nums = [1,2,3,2]
输出：14
解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
2 * (2+3+2) = 2 * 7 = 14 。
示例 2：

输入：nums = [2,3,3,1,2]
输出：18
解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
3 * (3+3) = 3 * 6 = 18 。
示例 3：

输入：nums = [3,1,5,6,4,2]
输出：60
解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
4 * (5+6+4) = 4 * 15 = 60 。


提示：

1 <= nums.length <= 105
1 <= nums[i] <= 107
通过次数1,297提交次数5,609
 */
import java.util.*;

public class medium_5752 {

    public static int maxSumMinProduct(int[] nums) {
        // 数组前缀和
        long[] pre = new long[nums.length];  // 此处要用long型数组，否则会越界
        pre[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }

        // 单递增调栈
        Stack<Integer> stack = new Stack<>();
        // 求元素右边第一个比其小的
        int[] rightLower = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 单调递增栈
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int t = stack.pop();
                rightLower[t] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int t = stack.pop();
            // 数组前后可以加上哨兵，前后都加一个 0；否则此处会出现越界情况，后续需要加特殊判断
            rightLower[t] = nums.length;
        }
        // 求元素左边第一个比其小的
        int[] leftLower = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int t = stack.pop();
                leftLower[t] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int t = stack.pop();
            leftLower[t] = -1;
        }

        // 在前缀和及单调栈基础上，求最终解
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int r = rightLower[i];
            int l = leftLower[i];
            long t;
            if (r == nums.length && l == -1) {
                t = pre[nums.length - 1];
            } else if (r == nums.length) {  // 越界情况特殊判断
                t = pre[nums.length - 1] - pre[l];
            } else if (l == -1) {  // 越界情况特殊判断
                t = pre[r] - nums[r];
            } else {
                t = pre[r] - pre[l] - nums[r];
            }
            ans = Math.max(ans, t * nums[i]);
        }
        return (int) (ans % 1000000007);
    }

    public static void main(String[] args) {
        int[] data = {555, 30, 5, 4, 2};
        System.out.println(maxSumMinProduct(data));
    }
}