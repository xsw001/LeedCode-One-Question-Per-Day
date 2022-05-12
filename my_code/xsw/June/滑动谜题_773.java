package xsw.June;
/*
在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.

一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.

最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。

给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。

示例：

输入：board = [[1,2,3],[4,0,5]]
输出：1
解释：交换 0 和 5 ，1 步完成
输入：board = [[1,2,3],[5,4,0]]
输出：-1
解释：没有办法完成谜板
输入：board = [[4,1,2],[5,0,3]]
输出：5
解释：
最少完成谜板的最少移动次数是 5 ，
一种移动路径:
尚未移动: [[4,1,2],[5,0,3]]
移动 1 次: [[4,1,2],[0,5,3]]
移动 2 次: [[0,1,2],[4,5,3]]
移动 3 次: [[1,0,2],[4,5,3]]
移动 4 次: [[1,2,0],[4,5,3]]
移动 5 次: [[1,2,3],[4,5,0]]
输入：board = [[3,2,4],[1,5,0]]
输出：14
提示：

board 是一个如上所述的 2 x 3 的数组.
board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
通过次数9,999提交次数15,148

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sliding-puzzle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class 滑动谜题_773 {

    public static int slidingPuzzle(int[][] board) {
        StringBuilder s = new StringBuilder();
        for (int[] ints : board) {
            for (int i : ints) {
                s.append(i);
            }
        }
        if (s.toString().equals("123450"))
            return 0;
        LinkedList<String> list = new LinkedList<>();
        list.add(s.toString());
        HashSet<String> visited = new HashSet<>();
        visited.add(s.toString());
        int ans = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            ++ans;
            for (int i = 0; i < size; i++) {
                String cur = list.poll();
                for (String next : help(cur)) {
                    if (next.equals("123450"))
                        return ans;
                    if (!visited.contains(next)) {
                        list.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }

    private static List<String> help(String cur) {
        ArrayList<String> list = new ArrayList<>();
        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[]{1, 3});
        map.put(1, new int[]{0, 2, 4});
        map.put(2, new int[]{1, 5});
        map.put(3, new int[]{0, 4});
        map.put(4, new int[]{1, 3, 5});
        map.put(5, new int[]{2, 4});
        int index = cur.indexOf('0');
        for (int i : map.get(index)) {
            char[] chars = cur.toCharArray();
            char temp = chars[i];
            chars[i] = chars[index];
            chars[index] = temp;
            list.add(new String(chars));
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] data = {{1,2,3},{4,0,5}};
        System.out.println(slidingPuzzle(data));

    }

}