package xsw.March;
/*

 */

public class medium_304 {


    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix obj = new NumMatrix(matrix);
        int param_1 = obj.sumRegion(1, 1, 2, 2);
        System.out.println(param_1);
    }

    static class NumMatrix1 {

        int[][] matrix;

        public NumMatrix1(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            while (row1 <= row2) {
                int i = col1;
                while (i <= col2) {
                    sum += matrix[row1][i++];
                }
                ++row1;
            }
            return sum;
        }
    }

    static class NumMatrix2 {

        int[][] matrix;

        public NumMatrix2(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                this.matrix = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        this.matrix[i][j + 1] = this.matrix[i][j] + matrix[i][j];
                    }
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            while (row1 <= row2) {
                sum += matrix[row1][col2 + 1] - matrix[row1][col1];
                ++row1;
            }
            return sum;
        }
    }

    static class NumMatrix {
        int[][] sum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sum = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
        }
    }
}