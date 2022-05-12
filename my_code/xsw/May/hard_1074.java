package xsw.May;
/*
1074. 元素和为目标值的子矩阵数量
给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。

子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。

如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。



示例 1：



输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
输出：4
解释：四个只含 0 的 1x1 子矩阵。
示例 2：

输入：matrix = [[1,-1],[-1,1]], target = 0
输出：5
解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
示例 3：

输入：matrix = [[904]], target = 0
输出：0


提示：

1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
通过次数9,959提交次数15,863
 */

//和363一样

import java.util.*;

public class hard_1074 {

    public static int numSubmatrixSumTarget(int[][] mat, int t) {
        int m = mat.length, n = mat[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] dp = new int[m];
            for (int j = i; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    dp[k] += mat[j][k];
                }
                ans += subarraySum(dp, t);
            }
        }
        return ans;
    }

    private static int subarraySum(int[] dp, int t) {
//扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        for (int i : dp) {
            sum += i;
            if(map.containsKey(sum-t)){
                res += map.get(sum-t);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}