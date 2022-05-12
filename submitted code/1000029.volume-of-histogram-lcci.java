//
// @lc app=leetcode.cn id=1000029 lang=java
//
// [1000029] volume-of-histogram-lcci
//
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int total = 0;
        for(int i : height)
            total += i;
        int l =0 ,r = len-1;
        int h = 1;
        int v = 0;
        while(l <= r){
            while(l <= r && height[l] < h)
                ++l;
            while(l <= r && height[r] < h)
                --r;
            v += r-l+1;
            ++h;
        }
        return v-total;
    }
}
// @lc code=end