//
// @lc app=leetcode.cn id=1985 lang=java
//
// [1985] maximum-subarray-min-product
//
class Solution {
    public int maxSumMinProduct(int[] nums) {
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
}
// @lc code=end