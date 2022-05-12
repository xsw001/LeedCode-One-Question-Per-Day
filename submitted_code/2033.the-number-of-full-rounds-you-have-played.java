//
// @lc app=leetcode.cn id=2033 lang=java
//
// [2033] the-number-of-full-rounds-you-have-played
//
class Solution {
    public int numberOfRounds(String startTime, String finishTime) {
        int ans = 0;
        String[] start = startTime.split(":");
        String[] finish = finishTime.split(":");
        int startHour = Integer.parseInt(start[0]);
        int finishHour = Integer.parseInt(finish[0]);
        int startMin = Integer.parseInt(start[1]);
        int finishMin = Integer.parseInt(finish[1]);
        if (start[0].equals(finish[0])) {
            if(startMin < finishMin) {
                ans += (30 - startMin) / 15;
                ans += (finishMin - 30) / 15;
            }else {
                ans += (60 - startMin) / 15;
                ans += finishMin / 15;
                ans += (finishHour + 24 - startHour - 1) * 4;
            }
            return ans;
        }
        ans += (60 - startMin) / 15;
        ans += finishMin / 15;
        if (startHour + 1 == finishHour)
            return ans;
        if (startHour > finishHour) {
            // 通宵
            ans += (finishHour + 24 - startHour - 1) * 4;
        } else {
            ans += (finishHour - startHour - 1) * 4;
        }
        return ans;
    }
}
// @lc code=end