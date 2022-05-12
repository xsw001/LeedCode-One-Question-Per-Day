//
// @lc app=leetcode.cn id=219 lang=java
//
// [219] contains-duplicate-ii
//
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0)
            return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i <= k && i < nums.length; i++) {
            set.add(nums[i]);
        }
        if(k >= nums.length)
            return set.size() != nums.length;
        int l = 0, r = k + 1;
        while (r < nums.length) {
            if (set.size() != k + 1)
                return true;
            set.remove(nums[l++]);
            set.add(nums[r++]);
        }
        return set.size() != k + 1;
    }
}
// @lc code=end