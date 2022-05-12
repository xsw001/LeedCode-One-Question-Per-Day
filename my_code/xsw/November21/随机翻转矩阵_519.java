package xsw.November21;
/*
519. 随机翻转矩阵
给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。

尽量最少调用内置的随机函数，并且优化时间和空间复杂度。

实现 Solution 类：

Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
void reset() 将矩阵中所有的值重置为 0


示例：

输入
["Solution", "flip", "flip", "flip", "reset", "flip"]
[[3, 1], [], [], [], [], []]
输出
[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

解释
Solution solution = new Solution(3, 1);
solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同


提示：

1 <= m, n <= 104
每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
最多调用 1000 次 flip 和 reset 方法。
通过次数6,993提交次数16,048
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class 随机翻转矩阵_519 {

    class Solution1 {
        //超出内存限制  2021/11/27 11:10

        ArrayList<int[]> list;
        int m, n;
        Random r;

        public Solution1(int _m, int _n) {
            list = new ArrayList<>();
            r = new Random();
            m = _m;
            n = _n;
            for (int i = 0; i < m * n; i++) {
                for (int j = 0; j < n; j++) {
                    list.add(new int[]{i, j});
                }
            }
        }

        public int[] flip() {
            return list.remove(r.nextInt(list.size()));
        }

        public void reset() {
            list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    list.add(new int[]{i, j});
                }
            }
        }
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    class Solution {
        //超出内存限制  2021/11/27 11:10

        HashMap<Integer, Integer> mm;
        ArrayList<Integer> lm;
        ArrayList<Integer> ln;
        HashMap<Integer, Integer> nn;
        int m, n;
        Random r;

        public Solution(int _m, int _n) {
            mm = new HashMap<>();
            nn = new HashMap<>();
            lm = new ArrayList<>();
            ln = new ArrayList<>();
            r = new Random();
            m = _m;
            n = _n;
            for (int i = 0; i < m; i++) {
                mm.put(i, 0);
                lm.add(i);
            }
            for (int i = 0; i < n; i++) {
                nn.put(i, 0);
                ln.add(i);
            }
        }

        public int[] flip() {
            int mi = r.nextInt(lm.size());
            int ni = r.nextInt(ln.size());
            Integer a = lm.get(mi);
            Integer b = ln.get(ni);
            if (mm.get(a) == n - 1)
                mm.remove(lm.remove(mi));
            else
                mm.put(a, mm.get(a) + 1);
            if (nn.get(b) == m - 1)
                nn.remove(ln.remove(ni));
            else
                nn.put(b, mm.get(b) + 1);
            return new int[]{a, b};
        }

        public void reset() {
            for (int i = 0; i < m; i++) {
                mm.put(i, 0);
                lm.add(i);
            }
            for (int i = 0; i < n; i++) {
                nn.put(i, 0);
                ln.add(i);
            }
        }
    }

}