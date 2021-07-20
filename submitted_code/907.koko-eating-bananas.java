//
// @lc app=leetcode.cn id=907 lang=java
//
// [907] koko-eating-bananas
//
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        while (l < r) {
            int mid = (l + r) / 2;
            int count = 0;
            for (int pile : piles) {
                if (pile <= mid) {
                    ++count;
                } else {
                    count += pile % mid == 0 ? pile / mid : pile / mid + 1;
                }
            }
            //如果天数超了, 说明运载能力有待提升, l 改大一点, 继续二分搜索
            if (count > h)
                l = mid + 1;
            else//否则运载能力改小一点继续搜索
                r = mid;
        }
        return l;
    }
}
// @lc code=end