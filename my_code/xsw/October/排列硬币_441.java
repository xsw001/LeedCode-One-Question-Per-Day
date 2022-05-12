package xsw.October;
/*
441. 排列硬币
你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。

给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。



示例 1：


输入：n = 5
输出：2
解释：因为第三行不完整，所以返回 2 。
示例 2：


输入：n = 8
输出：3
解释：因为第四行不完整，所以返回 3 。


提示：

1 <= n <= 231 - 1
通过次数51,549提交次数118,373
 */

import java.util.ArrayList;

public class 排列硬币_441 {

    public static int arrangeCoins1(int n) {
        int i = 1;
        long total = 1;
        while (total < n) {
            total += ++i;
        }
        return total > n ? i - 1 : i;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(arrangeCoins1(3));
        System.out.println(arrangeCoins(3));
        System.out.println(Integer.MAX_VALUE);
    }

    public static int arrangeCoins(int n) {
        long l = 1, r = 65535;
        while (l < r) {
            long mid = (l + r) / 2 + 1;
            if ((mid * (mid + 1)) / 2 > n) {
                r = mid - 1;
            } else
                l = mid;
        }
        return (int) l;
    }
}