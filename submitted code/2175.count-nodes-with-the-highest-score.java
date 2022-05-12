//
// @lc app=leetcode.cn id=2175 lang=java
//
// [2175] count-nodes-with-the-highest-score
//
class Solution {
    int[][] children;
    int ans,n;
    long maxScore;
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        children = new int[n][2];
        for (int[] child : children) {
            Arrays.fill(child, -1);
        }
        for (int i = 1; i < parents.length; i++) {
            if (children[parents[i]][0] == -1) {
                children[parents[i]][0] = i;
            } else
                children[parents[i]][1] = i;
        }
        ans = 0;
        maxScore = 0;
        count(0);
        return ans;
    }

    private int count(int cur) {
        if (cur == -1)
            return 0;
        int l = count(children[cur][0]);
        int r = count(children[cur][1]);
        int f = n - l - r - 1;
        long temp = help(l) * help(r) * help(f);
        if(temp > maxScore){
            ans = 1;
            maxScore = temp;
        }else if(temp == maxScore)
        ++ans;
        return l + r + 1;
    }

    private long help(int count) {
        return count == 0 ? 1 : count;
    }
}
// @lc code=end