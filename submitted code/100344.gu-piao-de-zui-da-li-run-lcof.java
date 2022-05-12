//
// @lc app=leetcode.cn id=100344 lang=java
//
// [100344] gu-piao-de-zui-da-li-run-lcof
//
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int min = 100004;
        for(int price : prices){
            if(price < min)
                min = price;
            if(price > min)
                ans = Math.max(ans,price-min);
        }
        return ans;
    }
}
// @lc code=end