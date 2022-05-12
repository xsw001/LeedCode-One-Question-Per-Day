//
// @lc app=leetcode.cn id=2195 lang=java
//
// [2195] time-needed-to-buy-tickets
//
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int num = tickets[k];
        int ans = 0;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < tickets.length; j++) {
                if(tickets[j] > 0){
                    tickets[j]--;
                    ans++;
                    if(tickets[k] == 0)
                        return ans;
                }
            }
        }
        return ans;
    }
}
// @lc code=end