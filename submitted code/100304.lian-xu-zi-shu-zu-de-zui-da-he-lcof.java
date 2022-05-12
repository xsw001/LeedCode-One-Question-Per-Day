//
// @lc app=leetcode.cn id=100304 lang=java
//
// [100304] lian-xu-zi-shu-zu-de-zui-da-he-lcof
//
class Solution {
    /*
     1.假如全是负数，那就是找最大值即可，因为负数肯定越加越大。 2.如果有正数，则肯定从正数开始计算和，不然前面有负值，和肯定变小了，所以从正数开始。 3.当和小于零时，这个区间就告一段落了，然后从下一个正数重新开始计算(也就是又回到 2 了)。而 dp 也就体现在这个地方。
    */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int l = nums.length, max = nums[0], pre = nums[0];
        for (int i = 1; i < l; i++) {
            if(pre > 0)
                pre += nums[i];
            else
                pre = nums[i];
            max = Math.max(max,pre);
        }
        return max;
    }
}
// @lc code=end