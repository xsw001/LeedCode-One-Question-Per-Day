//
// @lc app=leetcode.cn id=820 lang=java
//
// [820] find-eventual-safe-states
//
class Solution {
    /*
	 * 0：未访问
	 * 1：安全
	 * 2：已访问 || 成环
	 */
	public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<Integer>();//记录结果
        int n = graph.length;//长度
        int[] type = new int[n];//访问类型
        for ( int i = 0; i < n; i++ ) {
        	if ( DFS(graph, i, type) == 1 ) ans.add(i);
        }
        return ans;
    }
	
	public static int DFS( int[][] graph, int index, int[] type ) {
		if ( type[index] > 0 ) return type[index];	//如果不是0，返回自身
		type[index] = 2;//标记访问了
		for (int i : graph[index]) {
			if ( DFS(graph, i, type) == 2) {
				return 2;
			}
		}
		type[index] = 1;//不成环
		return 1;
	}
}
// @lc code=end