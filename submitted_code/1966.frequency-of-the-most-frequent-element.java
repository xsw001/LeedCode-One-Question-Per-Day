//
// @lc app=leetcode.cn id=1966 lang=java
//
// [1966] frequency-of-the-most-frequent-element
//
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);// 先排序
        int l = 0, r = 0; // 窗口的起始位置
        int ans = 0;        // 结果
        int sum = 0;        // 窗口里所有元素的和
        while (r < nums.length) {
            if (nums[r] * (r - l + 1) - sum - nums[r] <= k) { // 如果 k 够用，纪录最大，窗口继续扩大
                sum += nums[r]; // 累加遍历过的元素
                ans = Math.max(ans, r - l + 1);
                ++r;
            } else // 不够用了，窗口缩小扩大
                sum -= nums[l++]; // 保证sum的值
        }
        return ans;
    }
}
// @lc code=end