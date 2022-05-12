//
// @lc app=leetcode.cn id=475 lang=java
//
// [475] heaters
//
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int radius = 0;
        for (int house : houses) {
            int l = 0, r = heaters.length - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (house < heaters[m])
                    r = m;
                else
                    l = m + 1;
            }
            if (l == 0)
                radius = Math.max(radius, Math.abs(house - heaters[l]));
            else
                radius = Math.max(radius, Math.min(Math.abs(house - heaters[l - 1]), Math.abs(heaters[l] - house)));
        }
        return radius;
    }
}
// @lc code=end