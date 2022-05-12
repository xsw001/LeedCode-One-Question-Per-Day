package xsw.August;
/*
526. 优美的排列
假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，
使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：

第 i 位的数字能被 i 整除
i 能被第 i 位上的数字整除
现在给定一个整数 N，请问可以构造多少个优美的排列？

示例1:

输入: 2
输出: 2
解释:

第 1 个优美的排列是 [1, 2]:
  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

第 2 个优美的排列是 [2, 1]:
  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
说明:

N 是一个正整数，并且不会超过15。
通过次数19,087提交次数27,196
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
1
2
3
8
10
36
41
132
250
700
750
4010
4237
10680
24679
 */
public class 优美的排列_526 {

    static int ans;

    public static int countArrangement(int n) {
        ans = 0;
        boolean[] visited = new boolean[n + 1];
        backward(new ArrayList<Integer>(), visited, n);
        return ans;
    }

    private static void backward(ArrayList<Integer> path, boolean[] visited, int n) {
        if (n == path.size()) {
            ++ans;
            return;
        }
        for (int i = 1; i <= n; i++) {
            int index = path.size() + 1;
            if ((i % index == 0 || index % i == 0) && !visited[i]) {
                path.add(i);
                visited[i] = true;
                backward(path, visited, n);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    private static HashSet<Integer> compute(int num, int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            // 第 i 位的数字能被 i 整除 || i 能被第 i 位上的数字整除
            if (i % num == 0 || num % i == 0)
                set.add(i);
        }
        return set;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(countArrangement(3));
        System.out.println(Solution.countArrangement(6));
    }

    // 用 mask 的二进制表示选取状态，n 个数字用 n 位表示，
    // 第 i 位为 1 代表数字 i+1 已被选取（i从0开始），
    // n 中 1 的个数 m 代表前 m 位已放置
    // 例如：二进制 100110 共三个1，代表排列的前三位已放置数字，
    // 三个1分别在二进制第 1、2、5位置上(从右侧开始，从0开始计数）,
    // 所以 2、3、6三个数字被选取，综合起来就是表示：
    // 2 3 6 这三个数字被放到了排列的前三位，三个数字完美排列方式未知，通过枚举 mask 进行计算

    static class Solution {
        /* f[mask] 表示状态为 mask 时的可行方案总数
        状态转移方程的含义为
        当我们想要计算 f[mask] 时，我们只需要在前 num(mask)−1 位都已经放置了数的情况下
        考虑第 num(mask) 位要放置的数即可
         */
        public static int countArrangement(int n) {
            // 用来存储中间结果，f[6] = f[000110] = 数字2、3在前两位时的完美排列数量
            int[] f = new int[1 << n];
            f[0] = 1;
            // 通过 mask 进行枚举，最终目的是为了得到二进制 mask = (11..11)n 时，总的完美排列数
            // 若 mask 中的第 i 位为 1（从 0 开始编号），则数 i+1 已经被选取，否则就还未被选取
            for (int mask = 1; mask < (1 << n); mask++) {
//                if(mask == 38)
//                    System.out.println();
                int num = Integer.bitCount(mask);
                // 遍历 mask 的每一位，仍以 mask = 100110 为例，此 mask 代表 2 3 6三个数字在排列的前三位
                // 求三个数字 2 3 6 的完美排列方式，则先确定2 3 6哪些数字能放到第三位，然后累加另外两个数字的完美排列数量来获得
                // 2 3 6，第三位可以为 3，则 f[100110] += f[100010] (2、6在前两位时的完美排列数量)
                // 2 3 6，第三位可以为 6，则 f[100110] += f[000110] (2、3在前两位时的完美排列数量)
                for (int i = 0; i < n; i++) {
                    // mask & (1<<i) 用来判断 mask 第 i 位是否为 1，如果为 1，说明第 i+1 个数字被选取
                    // ((num % (i + 1)) == 0 || (i + 1) % num == 0) 判断被选取的数字 i+1 能否放到位置 num 上，
                    // 即：先从被选取的数字中找到能放到最高位 num 的数字，然后将剩余 num-1 个数字的完美排列方式累加到f[mask]中
                    if ((mask & (1 << i)) != 0 && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) {
                        // mask ^ (1 << i) 将 mask 第 i 位设置为 0
                        f[mask] += f[mask ^ (1 << i)];// 第 num 位可以把 i+1 放进去，那就放进去，然后累加没放进去时的完美排列数
                    }
                }
            }
            return f[(1 << n) - 1];
        }
    }
}