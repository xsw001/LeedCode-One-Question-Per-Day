//
// @lc app=leetcode.cn id=131 lang=java
//
// [131] palindrome-partitioning
//
class Solution {
    List<List<String>> res = new ArrayList<List<String>>();
    public List<List<String>> partition(String s) {
        dfs(s,new ArrayList<String>());
        return res;
    }

    public void dfs(String s, List<String> path) {
        if(s.length() == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=1;i<=s.length();++i){
            String str = s.substring(0,i);
            if(isString(str)){
                path.add(str);
                dfs(s.substring(i),path);
                path.remove(path.size()-1);
            }
        }
    }

    public boolean isString(String s){
        int l = s.length();
        if(l < 1){
            return true;
        }
        int left = 0, right = l-1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right))
                return false;
            ++left;
            --right;
        }
        return true;
    }
}
// @lc code=end