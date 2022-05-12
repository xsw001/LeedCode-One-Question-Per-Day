//
// @lc app=leetcode.cn id=189 lang=java
//
// [189] rotate-array
//
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        for (int start = 0; start < n; ++start) {
            if(count == n)
                return;
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                ++count;
            } while (start != current);
        }
    }
}

// @lc code=end