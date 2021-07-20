//
// @lc app=leetcode.cn id=239 lang=java
//
// [239] sliding-window-maximum
//
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1)
            return nums;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < k; ++i){
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()])
                deque.pollLast();
            deque.offerLast(i);
        }
        ans[0] = nums[deque.peekFirst()];
        for(int i = k; i<n;++i){
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()])
                deque.pollLast();
            deque.offerLast(i);
            while(deque.peekFirst() <= i-k)
                deque.pollFirst();
            ans[i-k+1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}

// @lc code=end