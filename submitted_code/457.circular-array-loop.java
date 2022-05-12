//
// @lc app=leetcode.cn id=457 lang=java
//
// [457] circular-array-loop
//
class Solution {
    public static boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return false;
        for (int i = 0; i < n; i++) {
            if (nums[i] % n != 0 && loop(i, nums)) {
                return true;
            }
        }
        return false;
    }

    private static boolean loop(int i, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        boolean flag = nums[i] > 0;
        boolean hasLoop = false;
        int pre = 0;

        while ((nums[i] > 0) == flag) {
            set.add(i);
            if (nums[i] == 0)
                break;
            pre = nums[i];
            nums[i] = 0;
            i = next(i, pre, n);

            if (set.contains(i)){
                hasLoop = true;
                break;
            }
        }
        return hasLoop && pre % n != 0;
    }

    private static int next(int i, int num, int n) {
        i += num;
        if (i > n - 1)
            i %= n;
        else if (i < 0) {
            i %= n;
            i += n;
        }
        return i;
    }
}
// @lc code=end