package xsw.November21;
/*
598. 范围求和 II
给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。

操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。

在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。

示例 1:

输入:
m = 3, n = 3
operations = [[2,2],[3,3]]
输出: 4
解释:
初始状态, M =
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

执行完操作 [2,2] 后, M =
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

执行完操作 [3,3] 后, M =
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
注意:

m 和 n 的范围是 [1,40000]。
a 的范围是 [1,m]，b 的范围是 [1,n]。
操作数目不超过 10000。
通过次数20,842提交次数38,723
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 范围求和II {

    // 超時
    public static int maxCount(int m, int n, int[][] ops) {
        int l = ops.length;
        if (l == 0)
            return m * n;
        Arrays.sort(ops, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o2[1] - o1[1];
            return o2[0] - o1[0];
        });
        int max = 0;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = 0;
                for (int[] op : ops) {
                    if (i >= op[0])
                        break;
                    if (j < op[1])
                        ++temp;
                }
                if (max == temp)
                    ++ans;
                else if (temp > max) {
                    max = temp;
                    ans = 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] data = {{2, 5}, {8, 13}, {15, 15}, {15, 5}, {5, 20}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(maxCount(5, 6, data));
    }

    class Solution {
        public int maxCount(int m, int n, int[][] ops) {
            int mina = m, minb = n;
            for (int[] op : ops) {
                mina = Math.min(mina, op[0]);
                minb = Math.min(minb, op[1]);
            }
            return mina * minb;
        }
    }
/*
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/range-addition-ii/solution/fan-wei-qiu-he-ii-by-leetcode-solution-kcxq/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}