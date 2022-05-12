package xsw.March.第234周赛;
/*
给你一个正整数 primeFactors 。你需要构造一个正整数 n ，它满足以下条件：

n 质因数（质因数需要考虑重复的情况）的数目 不超过 primeFactors 个。
n 好因子的数目最大化。如果 n 的一个因子可以被 n 的每一个质因数整除，我们称这个因子是 好因子 。比方说，如果 n = 12 ，那么它的质因数为 [2,2,3] ，那么 6 和 12 是好因子，但 3 和 4 不是。
请你返回 n 的好因子的数目。由于答案可能会很大，请返回答案对 109 + 7 取余 的结果。

请注意，一个质数的定义是大于 1 ，且不能被分解为两个小于该数的自然数相乘。一个数 n 的质因子是将 n 分解为若干个质因子，且它们的乘积为 n 。

示例 1：

输入：primeFactors = 5
输出：6
解释：200 是一个可行的 n 。
它有 5 个质因子：[2,2,2,5,5] ，且有 6 个好因子：[10,20,40,50,100,200] 。
不存在别的 n 有至多 5 个质因子，且同时有更多的好因子。
示例 2：

输入：primeFactors = 8
输出：18

提示：

1 <= primeFactors <= 109
 */

import java.util.Arrays;

public class LeedCode5716 {

    //超出内存限制
    public static int maxNiceDivisors1(int primeFactors) {
        if(primeFactors < 5)
            return primeFactors;
        long[] array = new long[primeFactors+1];
        for (int i = 0; i < 5; i++) {
            array[i] = i;
        }
        for (int i = 5; i < primeFactors + 1; i++) {
            long temp = 0;
            for (int j = 1; j <= i/2; j++) {
                temp = Math.max(temp,array[j]*array[i-j]);
            }
            array[i] = temp;
        }

        return (int) (array[primeFactors]%1000000007);
    }

    public static void main(String[] args) {
        int i = 3;
        long j = 3;
        System.out.println(i==j);
        System.out.println(maxNiceDivisors(4));
    }

    public static int maxNiceDivisors(int n) {
        int N = 10_0000_0007;
        if (n <= 3) {
            return n;
        }
        int a = n / 3, b = n % 3;
        if (b == 1) {
            return (int) (quickPow(3, a - 1, N) * 4 % N);
        } else if (b == 2) {
            return (int) (quickPow(3, a, N) * 2 % N);
        } else {
            return (int) quickPow(3, a, N);
        }
    }

    /**
     * 快速求幂：
     * p^q，计算中防止溢出，对MOD求余
     */
    public static long quickPow(int p, int q, int MOD) {
        long ans = 1L;
        long base = p;
        while (q != 0) {
            if ((q & 1) == 1) {
                ans = ans * base % MOD;
            }
            base = base * base % MOD;
            q >>>= 1;
        }
        return ans;
    }

}