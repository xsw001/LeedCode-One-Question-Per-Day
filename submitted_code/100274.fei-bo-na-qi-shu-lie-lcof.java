//
// @lc app=leetcode.cn id=100274 lang=java
//
// [100274] fei-bo-na-qi-shu-lie-lcof
//
class Solution {
    public int fib(int n) {
        if(n < 2)   return n;
        int[][] q = {{1,1},{1,0}};
        int[][] res = pow(q,n-1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n){
        int[][] ret = {{1,0},{0,1}};
        while(n > 0){
            if((n&1) == 1)
                ret = mul(ret,a);
            n >>=1;
            a = mul(a,a);
        }
        return ret;
    }

    public int[][] mul(int[][] a, int[][] b){
        int[][] c = new int[2][2];
        for(int i=0;i<2;++i){
            for(int j=0;j<2;++j){
                c[i][j] = (int) (((long)a[i][0]*b[0][j]+(long)a[i][1]*b[1][j]) % 1000000007);
            }
        }
        return c;
    }
}
// @lc code=end