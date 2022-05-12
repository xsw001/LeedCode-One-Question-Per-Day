//
// @lc app=leetcode.cn id=1047 lang=java
//
// [1047] maximize-sum-of-array-after-k-negations
//
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        int ans = 0;
        for (int num : nums) {
            queue.add(num);
            ans += num;
        }
        int K = k;
        for (int i = 0; i < k; i++) {
            Integer peek = queue.peek();
            if(peek >= 0)
                break;
            ans -= 2 * peek;
            queue.add(-queue.poll());
            --K;
        }
        return ans - (K % 2 == 0 ? 0 : 2 * queue.peek());
    }
}
// @lc code=end