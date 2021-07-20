//
// @lc app=leetcode.cn id=1538 lang=java
//
// [1538] maximum-points-you-can-obtain-from-cards
//
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int curCount = 0;
        int maxCount = 0;
        int i = 0;
        for(;i<k;i++){
            curCount += cardPoints[i];
        }
        maxCount = curCount;
        if(k==cardPoints.length){
            return maxCount;
        }
        int j = cardPoints.length;
        while(--i>=0){
            curCount -= cardPoints[i];
            curCount += cardPoints[--j];
            if(curCount>maxCount){
                maxCount = curCount;
            }
        }
        return maxCount;
    }
}
// @lc code=end