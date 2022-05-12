//
// @lc app=leetcode.cn id=1127 lang=java
//
// [1127] last-stone-weight
//
class Solution {
    public int lastStoneWeight(int[] stones) {
        Arrays.sort(stones);
        int l = stones.length - 1;
        while (l > 0) {
            if (stones[l] == stones[l - 1])
                l -= 2;
            else {
                stones[l - 1] = stones[l] - stones[l - 1];
                --l;
                Arrays.sort(stones);
            }
        }
        return l < 0 ? 0 : stones[l];
    }
}
// @lc code=end