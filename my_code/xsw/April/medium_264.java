package xsw.April;
/*
264. 丑数 II
给你一个整数 n ，请你找出并返回第 n 个 丑数 。

丑数 就是只包含质因数 2、3 和/或 5 的正整数。



示例 1：

输入：n = 10
输出：12
解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
示例 2：

输入：n = 1
输出：1
解释：1 通常被视为丑数。


提示：

1 <= n <= 1690
 */

import java.util.*;

public class medium_264 {

    public static int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        // System.out.println(seen);
        return ugly;
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber1(15));
        System.out.println(nthUglyNumber(15));
    }

    //用还没乘过 2 的最小丑数乘以 2；用还没乘过 3 的最小丑数乘以 3；用还没乘过 5 的最小丑数乘以 5。然后在得到的数字中取最小，就是新的丑数。
    public static int nthUglyNumber1(int n) {
        int[] factors = {2, 3, 5};
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] pointers = new int[3];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < pointers.length; j++) {
                int temp = dp[pointers[j]] * factors[j];
                min = Math.min(min, temp);
            }
            dp[i] = min;
            for (int j = 0; j < pointers.length; j++) {
                if (dp[i] == dp[pointers[j]] * factors[j]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }
}