package xsw.June.第244场周赛;
/*
5776. 判断矩阵经轮转后是否一致 显示英文描述
通过的用户数2712
尝试过的用户数2943
用户总通过次数2763
用户总提交次数5136
题目难度Easy
给你两个大小为 n x n 的二进制矩阵 mat 和 target 。现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。



示例 1：


输入：mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
输出：true
解释：顺时针轮转 90 度一次可以使 mat 和 target 一致。
示例 2：


输入：mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
输出：false
解释：无法通过轮转矩阵中的元素使 equal 与 target 一致。
示例 3：


输入：mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
输出：true
解释：顺时针轮转 90 度两次可以使 mat 和 target 一致。


提示：

n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] 和 target[i][j] 不是 0 就是 1
 */

import java.util.*;

public class LeedCode1 {

    public static boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.deepToString(mat));
            if(isTrue(mat,target))
                return true;
/*            if(Arrays.deepEquals(mat, target)){
                return true;
            }*/
            transfer(mat);
        }
        return  false;
    }

    private static void transfer(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n/2; i++) {
            int[] temp = mat[i];
            mat[i] = mat[n-i-1];
            mat[n-i-1] = temp;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

    private static boolean isTrue(int[][] mat, int[][] target) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j] != target[i][j])
                    return  false;
            }
        }
        return  true;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,1},{0,1}};
        int[][] tar = {{1,1},{1,0}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(findRotation(mat, tar));
    }

}