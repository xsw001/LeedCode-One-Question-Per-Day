//
// @lc app=leetcode.cn id=77 lang=java
//
// [77] combinations
//
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(n,k,0,res,path);
        return res;
    }

    public void dfs(int n, int k, int index,List<List<Integer>> res,List<Integer> path){
        if(path.size() + (n-index+1) < k)
            return;
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = index; i < n;++i){
            path.add(i+1);
            dfs(n,k,i+1,res,path);
            path.remove(path.size()-1);
        }
    }
}
// @lc code=end