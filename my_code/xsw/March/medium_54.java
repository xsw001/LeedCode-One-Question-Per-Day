package xsw.March;
/*
54. 螺旋矩阵
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。



示例 1：


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：


输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
通过次数112,897提交次数257,286
 */

import java.util.ArrayList;
import java.util.List;

public class medium_54 {

    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> results = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int i = 0, j = 0;
        int count = 0;
        int turn = 0;//控制方向
        while (count < m * n) {
            if (i >= 0 && j >= 0 && i < m && j < n && !visited[i][j]) {
                results.add(matrix[i][j]);
                visited[i][j] = true;
                switch (turn) {
                    case 0 -> ++j;
                    case 1 -> ++i;
                    case 2 -> --j;
                    case 3 -> --i;
                }
                ++count;
            } else {
                turn = (turn + 1) % 4;
                if (i == -1 || turn == 0) {
                    ++i;
                    ++j;
                } else if (j == n || turn == 1) {
                    --j;
                    ++i;
                } else if (i == m || turn == 2) {
                    --i;
                    --j;
                } else if (j == -1 || turn == 3) {
                    ++j;
                    --i;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> results = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int i = 0, j = 0;
        int count = m * n;
        int turn = 0;//控制方向
        while (count > 0) {
            switch (turn) {
                case 0 -> { // 向右  →
                    while (j < n && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i][j++]);
                        --count;
                    }
                    --j;
                    ++i;
                }
                case 1 -> {// 向下  ↓
                    while (i < m && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i++][j]);
                        --count;
                    }
                    --i;
                    --j;
                }
                case 2 -> { // 向左  ←
                    while (j > -1 && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i][j--]);
                        --count;
                    }
                    ++j;
                    --i;
                }
                case 3 -> {// 向上  ↑
                    while (i > -1 && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i--][j]);
                        --count;
                    }
                    ++j;
                    ++i;
                }
            }
            turn = (turn + 1) % 4;
        }
        return results;
    }

    //空间 o（1）
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0)
            return list;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;

        //统计矩阵从外向内的层数，如果矩阵非空，那么它的层数至少为1层
        int count = (Math.min(m, n)+1)/2;
        //从外部向内部遍历，逐层打印数据
        while(i < count) {
            for (int j = i; j < n-i; j++) {//从左向右
                list.add(matrix[i][j]);
            }
            for (int j = i+1; j < m-i; j++) {//从上往下
                list.add(matrix[j][(n-1)-i]);
            }

            for (int j = (n-1)-(i+1); j >= i && (m-1-i != i); j--) {
                list.add(matrix[(m-1)-i][j]);//从右往左，如果这一层只有1行，那么第一个循环已经将该行打印了，这里就不需要打印了
            }
            for (int j = (m-1)-(i+1); j >= i+1 && (n-1-i) != i; j--) {
                list.add(matrix[j][i]);//从下往上，如果这一层只有1列，那么第2个循环已经将该列打印了，这里不需要打印
            }
            i++;
        }
        return list;
    }
}