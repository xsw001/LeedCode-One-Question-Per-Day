//
// @lc app=leetcode.cn id=100332 lang=java
//
// [100332] zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
//
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, length = s.length(), r = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            while (r < length && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                ++r;
            }
            ans = Math.max(ans, set.size());
            set.remove(s.charAt(i));
        }
        return ans;
    }
}
// @lc code=end