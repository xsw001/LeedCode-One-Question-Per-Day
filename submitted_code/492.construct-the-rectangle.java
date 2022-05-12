//
// @lc app=leetcode.cn id=492 lang=java
//
// [492] construct-the-rectangle
//
class Solution {
    public int[] constructRectangle(int area) {
        double v = Math.sqrt(area);
        int in = (int) v;
        if (in == v)
            return new int[]{in, in};
        for (int i = in; i > 0; i--) {
            if (area % i == 0)
                return new int[]{area / i, i};
        }
        return new int[]{area, 1};
    }
}
// @lc code=end