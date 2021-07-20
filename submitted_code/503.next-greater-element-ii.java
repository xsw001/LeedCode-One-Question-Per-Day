//
// @lc app=leetcode.cn id=503 lang=java
//
// [503] next-greater-element-ii
//
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()])
                res[stack.pop()] = nums[i % n];
            stack.push(i % n);
        }
        return res;
    }
}
// @lc code=end