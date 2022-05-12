package xsw.August;
/*
313. 超级丑数
超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。

给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。

题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。



示例 1：

输入：n = 12, primes = [2,7,13,19]
输出：32
解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
示例 2：

输入：n = 1, primes = [2,3,5]
输出：1
解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。

提示：

1 <= n <= 106
1 <= primes.length <= 100
2 <= primes[i] <= 1000
题目数据 保证 primes[i] 是一个质数
primes 中的所有值都 互不相同 ，且按 递增顺序 排列
通过次数25,444提交次数38,379
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.*;

public class 超级丑数_313 {

    // 做过，忘了，一点西路都没。。。。。。就是264的原题啊，代码就没变
    public static int nthSuperUglyNumber1(int n, int[] primes) {
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            list.add(ugly);
            for (int prime : primes) {
                long next = curr * prime;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        System.out.println(list);
        return ugly;
    }

    public static void main(String[] args) {
        int n = 25;
        int[] data = {2, 7, 13, 19};
        System.out.println(nthSuperUglyNumber1(n, data));
        System.out.println(nthSuperUglyNumber(n, data));
        System.out.println(nthSuperUglyNumber2(n, data));

    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 2; i < n + 1; i++) {
            int min = Integer.MAX_VALUE, index = 1;
            for (int k = 0; k < m; k++) {
                int temp = dp[pointers[k]] * primes[k];

                if (temp < min) {
                    if (!visited.contains(temp)) {
                        min = temp;
                        index = k;
                    }
                }
            }
            dp[i] = min;
            visited.add(min);
            pointers[index]++;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    /**
     * 考虑新丑数的诞生方式，简单推算可得：
     * ​        旧丑数序列 * 质数序列 = 新丑数序列
     * 因为存在新旧的转移关系，可以考虑用动态规划解决此题。
     * 因为题目要求第n个丑数，即我们需要从第一个丑数不断推移至第n个丑数，我们需要考虑已知第一个丑数，如何找出第二个丑数。
     * 因为已知： 旧丑数序列 * 质数序列 = 新丑数序列
     * 则我们把质数序列依次乘于第一个丑数后，就可以得到新丑数序列，新丑数序列中【最小】的丑数，便是第二个丑数
     * 以此类推：已知前n个丑数，如何得出第n + 1个丑数
     * 仍然是： 旧丑数序列 * 质数序列 = 新丑数序列
     * 但是： 旧丑数序列依次乘于质数序列将会出现 n * n的复杂度，有没有办法优化呢？
     * 优化方案：因为我们要求出的是下一个最小的丑数，当旧丑数序列中的 一个丑数 和 一个质数 已经贡献出过一个最小值
     * 则它们肯定无法再次贡献出最小值，即不考虑该质数与这个丑数的乘积，只考虑该质数下的下一个丑数与它的乘积。
     * 那么，我们再定义一个【pointer数组，用于记录每个质数应该与哪个旧丑数做乘积】即可
     */
    public static int nthSuperUglyNumber2(int n, int[] primes) {
        int m = primes.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] pointers = new int[m];//记录质数该与哪一位丑数做乘积
        Arrays.fill(pointers, 1);
        for (int i = 2; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < m; k++) {
                int temp = dp[pointers[k]] * primes[k];//旧丑数 * 质数序列 = 新丑数序列

                if (temp < min) {
                    min = temp;//寻找所有新丑数中最小的丑数
                }

            }
            dp[i] = min;
            for (int j = 0; j < pointers.length; j++) {
                if (dp[i] == dp[pointers[j]] * primes[j]) {//如果此位置已经诞生过最小丑数
                    pointers[j]++; //让此位置所取旧丑数向后推一位
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}