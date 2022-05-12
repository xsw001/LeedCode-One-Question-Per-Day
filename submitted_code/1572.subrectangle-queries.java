//
// @lc app=leetcode.cn id=1572 lang=java
//
// [1572] subrectangle-queries
//
class SubrectangleQueries {
    private int[][] rectangle;

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        while (row1 <= row2){
            int i = col1;
            while (i <= col2){
                rectangle[row1][i] = newValue;
                ++i;
            }
            ++row1;
        }
    }

    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */
// @lc code=end