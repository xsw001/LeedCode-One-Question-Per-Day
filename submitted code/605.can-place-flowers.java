//
// @lc app=leetcode.cn id=605 lang=java
//
// [605] can-place-flowers
//
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int num = 0;
        int l = flowerbed.length;
        for (int i = 0; i < l; i++) {
            if (flowerbed[i] == 1) {
                if (i > 0)
                    flowerbed[i - 1] = 1;
                if (i < l - 1)
                    flowerbed[i + 1] = 1;
            } else
                ++num;
            ++i;
        }
        return num >= n;
    }
}
// @lc code=end