//
// @lc app=leetcode.cn id=547 lang=java
//
// [547] number-of-provinces
//
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int cnt = 0;
        for(int i = 0; i < n ; ++i){
            if(!visited[i]){
                dfs(i,isConnected,visited);
                ++cnt;
            }
        }
        return cnt;
    }
    private void dfs(int i, int[][] isConnected, boolean[] visited) {
        visited[i] = true;

        for(int j = 0; j < isConnected.length; ++j){
            if(isConnected[i][j] == 1 && !visited[j]){
                dfs(j,isConnected,visited);
            }
        }
    }
}
// @lc code=end