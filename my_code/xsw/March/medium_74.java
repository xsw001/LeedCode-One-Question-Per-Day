package xsw.March;
/*
74. 搜索二维矩阵
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。


示例 1：


输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
示例 2：


输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
 */

public class medium_74 {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int begin = matrix[0][0],end = matrix[m-1][n-1];
        if(target < begin || target > end)
            return false;
        int[] group = new int[m];
        for (int i = 0; i < m; i++) {
            group[i] = matrix[i][n-1];
        }
        int j = 0;
        for (int i = 0; i < m; i++) {
            if(group[i] == target)
                return true;
            if (group[i] > target){
                j = i;
                break;
            }
        }
        int[] res = matrix[j];
       return binarySearch(res,target);
    }

    private static boolean binarySearch(int[] res, int target) {
        int l = 0, r = res.length-1;
        while(l <= r){
            int m = (l+r)/2;
            if(res[m] == target)
                return true;
            if(res[m] < target)
                l = m+1;
            else
                r = m-1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
    }

}