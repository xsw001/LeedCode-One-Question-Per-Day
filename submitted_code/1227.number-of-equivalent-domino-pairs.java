//
// @lc app=leetcode.cn id=1227 lang=java
//
// [1227] number-of-equivalent-domino-pairs
//
class Solution {
    public static int numEquivDominoPairs(int[][] dominoes) {
        int res = 0;
        int l = dominoes.length;
        boolean[] flag = new boolean[l];
        for(int i = 0; i < l; i++){
            int temp = 0;
            for (int j = i+1; j < l; j++) {
                if(!flag[j] && isEqu(dominoes[i],dominoes[j])){
                    ++temp;
                    flag[j] = true;
                }
            }
            res += temp*(temp+1)/2;
        }
        return res;
    }

    private static boolean isEqu(int[] x, int[] y) {
        if(x[0] == y[0] && x[1] == y[1])
            return true;
        return x[0] == y[1] && x[1] == y[0];
    }

}
// @lc code=end