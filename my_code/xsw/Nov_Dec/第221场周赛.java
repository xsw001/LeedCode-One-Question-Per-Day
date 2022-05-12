package xsw.Nov_Dec;

/*

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 第221场周赛 {


    public static void main(String[] args) {
/*        int[] apples = {3, 0, 0, 0, 0, 2};
        int[] days = {3, 0, 0, 0, 0, 2};*//*
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        int app = eatenApples(apples, days);
        System.out.println(app);*/
        //int[][] grid = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};
        int[][] grid = {{-1,1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,1,-1,-1,-1,1,1,1,-1,-1,1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,1,-1,-1,1,1,-1,1,-1,-1,-1,-1,1,1,1,1,1,1,-1,1,1,1,-1,1,1,1,-1,-1,-1,1,-1,1,-1,-1,1,1,-1,-1,1,-1,1,-1,1,1,1,-1,-1,-1,-1}};
/*        int[][] grid = {
                {1,-1,-1,1,-1,1,1,1,1,1,-1,1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,1,-1,1,-1,1,-1,-1,-1,-1,1,-1,1,1,-1,-1,-1,-1,-1,1},
                {-1,1,1,1,-1,-1,-1,-1,1,1,1,-1,-1,-1,1,-1,-1,1,1,1,1,1,1,-1,1,-1,-1,-1,-1,-1,1,-1,1,-1,-1,-1,-1,1,1,-1,1,1},
                {1,-1,-1,-1,-1,1,-1,1,1,1,1,1,1,1,-1,1,-1,-1,-1,1,-1,-1,1,-1,1,-1,1,-1,-1,1,-1,1,-1,1,1,-1,-1,1,1,-1,1,-1}};
        */
        int[] ball = findBall(grid);
        System.out.println(Arrays.toString(ball));
    }

    public static boolean halvesAreAlike(String s) {
        ArrayList<Character> list = new ArrayList<>();
        Collections.addAll(list, 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2, s.length());
        int numA = 0, numB = 0;
        for (int i = 0; i < a.length(); i++) {
            if (list.contains(a.charAt(i)))
                ++numA;
            if (list.contains(b.charAt(i)))
                ++numB;
        }
        return numA == numB;
    }

    public static int eatenApples(int[] apples, int[] days) {
        return 0;
    }
    //42 / 62 个通过测试用例
    public static int[] findBall1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int[] step = helper1(grid, i);
            if (step[0] == m)
                result[i] = step[1];
            else
                result[i] = -1;
        }
        if (grid[0][n - 1] == 1) {
            result[n - 1] = -1;
        }
        if (grid[0][0] == -1) {
            result[0] = -1;
        }
        return result;
    }
    private static int[] helper1(int[][] grid, int index) {
        int m = grid.length;
        int n = grid[0].length;
        int i = 0;
        boolean flag = true;
        while (i < m) {
            if ((index < n - 1 && grid[i][index] == 1 && grid[i][index + 1] == -1) || (index > 0 && grid[i][index] == -1 && grid[i][index - 1] == 1))
                return new int[]{i, index};
            if (grid[i][index] == 1) {
                if (flag && i == m - 1 && index < n - 1 && grid[i][index + 1] == 1) {
                    ++index;
                    break;
                }
                if (index < n - 1 && i < m - 1 && grid[i][index + 1] == 1 && 1 == grid[i + 1][index]) {
                    ++i;
                    ++index;
                } else {
                    ++i;
                }
            } else {
                if (flag && i == m - 1 && index > 0 && grid[i][index - 1] == -1) {
                    --index;
                    break;
                }
                if (index > 0 && i < m - 1 && grid[i][index - 1] == -1 && -1 == grid[i + 1][index]) {
                    ++i;
                    --index;
                } else {
                    ++i;
                }
            }
            flag = false;
        }
        return new int[]{i, index};
    }
//执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗：39.8 MB, 在所有 Java 提交中击败了100.00%的用户
    public static int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int[] step = helper(grid, i);
            if (step[0] == m)
                result[i] = step[1];
            else
                result[i] = -1;
        }
        if (grid[0][n - 1] == 1) {
            result[n - 1] = -1;
        }
        if (grid[0][0] == -1) {
            result[0] = -1;
        }
        return result;
    }
    private static int[] helper(int[][] grid, int index) {
        int m = grid.length;
        int n = grid[0].length;
        int i = 0;
        while (i < m) {
            if ((index < n - 1 && grid[i][index] == 1 && grid[i][index + 1] == -1) || (index > 0 && grid[i][index] == -1 && grid[i][index - 1] == 1))
                return new int[]{i, index};
            if (grid[i][index] == 1) {
                index++;
            } else if (grid[i][index] == -1){
                index--;
            }
            if(index >= n)
                break;
            ++i;
        }
        return new int[]{i, index};
    }
}
