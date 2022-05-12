//
// @lc app=leetcode.cn id=1605 lang=java
//
// [1605] minimum-number-of-days-to-make-m-bouquets
//
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (k * m > bloomDay.length) {
            return -1;
        }
        int low = Arrays.stream(bloomDay).min().getAsInt(), high = Arrays.stream(bloomDay).max().getAsInt();
        while(low <= high){
            int mid = (low+high)/2;
            if(canMake(bloomDay,mid,m,k))
                high = mid-1;
            else
                low = mid+1;
        }
        return low;
    }

    public boolean canMake(int[] bloomDay, int days, int m, int k) {
        int bouquets = 0;
        int flowers = 0;
        int length = bloomDay.length;
        for(int i=0;i < length && bouquets < m;++i){
            if(bloomDay[i] <= days){
                ++flowers;
                if(flowers == k){
                    ++bouquets;
                    flowers = 0;
                }
            }else{
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
// @lc code=end