package xsw.September;
/*
650. 只有两个键的键盘
最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：

Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
Paste（粘贴）：粘贴 上一次 复制的字符。
给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。



示例 1：

输入：3
输出：3
解释：
最初, 只有一个字符 'A'。
第 1 步, 使用 Copy All 操作。
第 2 步, 使用 Paste 操作来获得 'AA'。
第 3 步, 使用 Paste 操作来获得 'AAA'。
示例 2：

输入：n = 1
输出：0


提示：

1 <= n <= 1000
通过次数28,342提交次数52,247
 */

import java.util.ArrayList;
import java.util.HashSet;

public class 只有两个键的键盘_650 {

    static int ans;
    static int num;

    public static int minSteps(int n) {
        num = 0;
        if (n == 1)
            return 0;
        ans = n;
        back(n, 1, 1, 1, false);
        return ans;
    }

    private static void back(int n, int count, int step, int CtrlC, boolean lastCopy) {
        ++num;
        if (count == n) {
            ans = Math.min(ans, step);
            return;
        }
        if (count > n || step > ans)
            return;
        if (!lastCopy) // 不允许连续两次复制
            back(n, count, step + 1, count, true);
        back(n, count + CtrlC, step + 1, CtrlC, false);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            minSteps(i);
            if (num > i * i/3)
                ++count;
        }
        System.out.println(count);
    }

    static class Solution {
        /*
        设 f[i] 表示打印出 i 个 A 的最少操作次数。由于我们只能使用「复制全部」和「粘贴」两种操作
        那么要想得到 i 个 A，我们必须首先拥有 j 个 A，使用一次「复制全部」操作，再使用若干次「粘贴」操作得到 i 个 A。
        因此，这里的 j 必须是 i 的因数，「粘贴」操作的使用次数即为 i/j−1。我们可以枚举 j 进行状态转移
         */
        public int minSteps(int n) {
            int[] f = new int[n + 1];
            for (int i = 2; i <= n; ++i) {
                f[i] = Integer.MAX_VALUE;
                //一种时间复杂度更低的方法是，我们只在 [1, sqrt{i}] 的范围内枚举 j，并用 j 和 i/j 分别作为因数进行状态转移
                for (int j = 1; j * j <= i; ++j) {
                    if (i % j == 0) {
                        f[i] = Math.min(f[i], f[j] + i / j);
                        f[i] = Math.min(f[i], f[i / j] + j);
                    }
                }
            }
            return f[n];
        }
    }

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/2-keys-keyboard/solution/zhi-you-liang-ge-jian-de-jian-pan-by-lee-ussa/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}