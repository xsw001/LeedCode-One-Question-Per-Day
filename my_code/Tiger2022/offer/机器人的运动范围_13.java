package Tiger2022.offer;
/*
剑指 Offer 13. 机器人的运动范围
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？



示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1
提示：

1 <= n,m <= 100
0 <= k <= 20
 */

import org.junit.Test;

import java.util.HashSet;

public class 机器人的运动范围_13 {

    @Test
    public void test() throws Exception {
        System.out.println(movingCount(50, 50, 4));
    }

    HashSet<Integer> ans;
    int mm, nn;

    public int movingCount(int m, int n, int k) {
        ans = new HashSet<>();
        mm = m;
        nn = n;
        really(0, 0, k, new HashSet<>());
        return ans.size();
    }

    private void really(int i, int j, int k, HashSet<Integer> visted) {
        if (i < 0 || i >= mm || j < 0 || j >= nn || visted.contains(i * nn + j) || ans.contains(i * nn + j) || !can(i, j, k))
            return;
        if (can(i, j, k)) {
            ans.add(i * nn + j);
        }
        System.out.println(i * nn + j);
        visted.add(i * nn + j);
        really(i + 1, j, k, visted);
        really(i - 1, j, k, visted);
        really(i, j + 1, k, visted);
        really(i, j - 1, k, visted);
        visted.remove(i * nn + j);
    }

    private boolean can(int i, int j, int k) {
        int t = 0;
        while (i > 0) {
            t += i % 10;
            i /= 10;
        }
        while (j > 0) {
            t += j % 10;
            j /= 10;
        }
        return t <= k;
    }
}
