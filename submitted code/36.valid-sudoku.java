//
// @lc app=leetcode.cn id=36 lang=java
//
// [36] valid-sudoku
//
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] index = {1, 4, 7, 28, 31, 34, 55, 58, 61};
        HashMap<String, HashSet<Character>> map = new HashMap<>();        
        for (int i = 0; i < 9; i++) {
            map.put("r" + i, new HashSet<Character>());
            map.put("c" + i, new HashSet<Character>());
        }
        HashSet<Character> temp = new HashSet<>();
        for (int i : index) {
            int row = (i - 1) / 9; //行
            int column = (i - 1) % 9; // 列
            for (int a = row; a < row + 3; a++) {
                for (int b = column; b < column + 3; b++) {
                    char c = board[a][b];
                    if(c == '.')
                        continue;
                    if (temp.contains(c) || map.get("r" + a).contains(c) || map.get("c" + b).contains(c))
                        return false;
                    temp.add(c);
                    map.get("r" + a).add(c);
                    map.get("c" + b).add(c);
                }
            }
            temp.clear();
        }
        return true;
    }
}
// @lc code=end