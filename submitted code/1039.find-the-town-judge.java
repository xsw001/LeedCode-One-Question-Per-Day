//
// @lc app=leetcode.cn id=1039 lang=java
//
// [1039] find-the-town-judge
//
class Solution {
    public int findJudge(int n, int[][] trust) {
        if(n == 1)
            return 1;
        int[] count = new int[n+1];
        int[] ty = new int[n+1];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int[] arr : trust){
            count[arr[1]]++;
            ty[arr[0]]++;
            if(count[arr[1]] == n-1)
                ans.add(arr[1]);
        }
        for(int i : ans)
            if(ty[i] == 0)
                return i;
        return -1;
    }
}
// @lc code=end