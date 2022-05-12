package xsw.April.第238周赛;
/*
5739. 最高频元素的频数 显示英文描述
通过的用户数7
尝试过的用户数8
用户总通过次数7
用户总提交次数8
题目难度Medium
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
 */

import java.util.Arrays;

public class medium_5739 {

    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int j = 0;
        long a = 0;
        for (int i = 1; i < nums.length; i++) {
            //计算区间内每个值，与区间内最后一个值相差的总和
            a += (nums[i] - nums[i - 1]) * (i - j);
            //如果超过目标值
            while (a > k) {
                //那么就减去区间内最左侧的值与最后一个值的差距。
                //然后再让区间左侧向右移动一位，相等于整个区间缩小了一位距离，在缩小的区间内再判断是否满足要求
                a -= nums[i] - nums[j];
                j++;
            }
            ans = Math.max(i - j + 1, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {1,2,4,6};
        System.out.println(maxFrequency2(data,5));
    }

    public static int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int left = 0,len = 0;
        //想象成矩形面积
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // i - left + 1      表示长度
            // nums[i] * temp - sum      表示操作次数
            while (nums[i] * (i - left + 1) - sum > k) {//如果操作次数 > k
                sum -= nums[left];  //就要减去最小的左边界
                left++;// 左边界右移
            }
            len = Math.max(len, i - left + 1);
        }
        return len;
    }
}