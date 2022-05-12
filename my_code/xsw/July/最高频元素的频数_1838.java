package xsw.July;
/*
元素的 频数 是该元素在一个数组中出现的次数。

给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。

执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。

 

示例 1：

输入：nums = [1,2,4], k = 5
输出：3
解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。
示例 2：

输入：nums = [1,4,8,13], k = 5
输出：2
解释：存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
示例 3：

输入：nums = [3,9,6], k = 2
输出：1
 

提示：

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 最高频元素的频数_1838 {

    // 前缀和有点多余
    public static int maxFrequency1(int[] nums, int k) {
        Arrays.sort(nums);
        int[] pre = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        int l = 0, r = 0;
        int ans = 0;
        int sum = nums[0];
        while (r < nums.length) {
            if (nums[r] * (r - l + 1) - (pre[r + 1] - pre[l]) <= k) {
                ans = Math.max(ans, r - l + 1);
                ++r;
            } else {
                ++l;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);// 先排序
        int l = 0, r = 0; // 窗口的起始位置
        int ans = 0;        // 结果
        int sum = 0;        // 窗口里所有元素的和
        while (r < nums.length) {
            sum += nums[r]; // 累加遍历过的元素
            if (nums[r] * (r - l + 1) - sum <= k) { // 如果 k 够用，纪录最大，窗口继续扩大
                ans = Math.max(ans, r - l + 1);
                ++r;
            } else {// 不够用了，窗口缩小扩大
                ++l;
                sum -= nums[l]; // 保证sum的值
            }
        }
        return ans;
    }
}