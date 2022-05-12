//
// @lc app=leetcode.cn id=458 lang=java
//
// [458] poor-pigs
//
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int times = minutesToTest / minutesToDie + 1;
        double time = Math.log(buckets) / Math.log(times);
        return (int)Math.ceil(time);
    }
}

// @lc code=end