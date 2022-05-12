//
// @lc app=leetcode.cn id=121 lang=c
//
// [121] best-time-to-buy-and-sell-stock
//
int maxProfit(int* prices, int pricesSize){
    if(pricesSize==0)
        return 0;
    int i,j,min=prices[0],max=0;
    for(i=1;i<pricesSize;i++){
        if(prices[i]<min)
            min=prices[i];
        else if(prices[i]-min>max)
            max=prices[i]-min;
    }
    return max;
}
// @lc code=end