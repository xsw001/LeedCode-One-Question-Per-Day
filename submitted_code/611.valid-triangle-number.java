//
// @lc app=leetcode.cn id=611 lang=java
//
// [611] valid-triangle-number
//
class Solution {
    public static int triangleNumber(int[] nums) {
        int size = nums.length;
        if (size < 3)
            return 0;
        int ans = 0;
        Arrays.sort(nums);
        int i = 0;
        if (nums[0] == 0)
            ++i;
        for (; i < size; i++) {
            int a = nums[i];
            for (int j = i + 1; j < size - 1; j++) {
                int b = nums[j];
                int max = b + a - 1;
                int l = j + 1, r = size - 1;
                while (l < r) {
                    int c = (l + r + 1) / 2;
                    if (nums[c] > max)
                        r = c - 1;
                    else
                        l = c;
                }
                if (l == j + 1 && !triangle(a, b, nums[l]))
                    continue;
                ans += (l - j);
            }
        }
        return ans;
    }

    private static boolean triangle(int a, int b, int c) {
        return a + b > c && Math.abs(b - a) < c;
    }
}
// @lc code=end