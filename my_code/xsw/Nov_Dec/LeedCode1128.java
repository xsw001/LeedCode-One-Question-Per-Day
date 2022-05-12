package xsw.Nov_Dec;
/*
1128. 等价多米诺骨牌对的数量
给你一个由一些多米诺骨牌组成的列表 dominoes。

如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。



示例：

输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1


提示：

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LeedCode1128 {

    //超出时间限制
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


    public static void main(String[] args) {
        int[][] dominoes = {{1,2},{5,6}};
        System.out.println(numEquivDominoPairs(dominoes));
    }

    static class Solution {

        public int numEquivDominoPairs(int[][] dominoes) {
            int[] freq = new int[100];

            int count = 0;
            for (int[] dominoe : dominoes) {
                if (dominoe[0] > dominoe[1]) {
                    int temp = dominoe[0];
                    dominoe[0] = dominoe[1];
                    dominoe[1] = temp;
                }

                int num = dominoe[0] * 10 + dominoe[1];
                count += freq[num];//count就是进行了等差数列的累加，即1+2+3...+N，实际上，就是1+2+3...+N=N*(N-1)/2
                freq[num]++;
            }
            return count;
        }
    }
}