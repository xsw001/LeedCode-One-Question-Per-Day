package xsw.January;
/*
766. 托普利茨矩阵
给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。

如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。



示例 1：


输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
输出：true
解释：
在上述矩阵中, 其对角线为:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
各条对角线上的所有元素均相同, 因此答案是 True 。
示例 2：


输入：matrix = [[1,2],[2,2]]
输出：false
解释：
对角线 "[1, 2]" 上的元素不同。


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 20
0 <= matrix[i][j] <= 99
 */

import java.util.*;

public class LeedCode766 {

    public static boolean isToeplitzMatrix(int[][] matrix) {
        int i = 0;
        while(i < matrix.length){
            int ii = 0,iii = i;;
            int num = matrix[i][ii];
            while(i < matrix.length && ii < matrix[0].length){
                if(num != matrix[i][ii])
                    return false;
                ++i;
                ++ii;
            }
            i = iii+1;
        }
        int j = 1;
        while(j < matrix[0].length){
            int jj = 0,jjj = j;
            int num = matrix[jj][j];
            while(jj < matrix.length && j < matrix[0].length){
                if(num != matrix[jj][j])
                    return false;
                ++j;
                ++jj;
            }
            j = jjj+1;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        System.out.println(isToeplitzMatrix(matrix));
    }
}