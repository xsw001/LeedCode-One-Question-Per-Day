package xsw.Nov_Dec;/*
204. 计数质数
统计所有小于非负整数 n 的质数的数量。



示例 1：

输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
示例 2：

输入：n = 0
输出：0
示例 3：

输入：n = 1
输出：0
提示：0 <= n <= 5 * 106
*/

import java.util.ArrayList;
import java.util.Arrays;

public class LeedCode204 {

    //超时
    public static int countPrimes1(int n) {
        if (n < 3)
            return 0;
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i))
                ++count;
        }
        return count;
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j*j <= i; j++) {
            if (i % j == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 234234;
        int i = countPrimes2(n);
        int j = countPrimes(n);
        System.out.println(i);
        System.out.println(j);
    }

    //依旧超出时间限制
    public static int countPrimes(int n) {
        if (n < 3)
            return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        for (int i = 3; i < n; i++) {
            int flag = 0;
            for (int integer : list) {
                if (i % integer == 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                list.add(i);
        }
        return list.size();
    }


    public static int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                /*应该直接从 x⋅x 开始标记，因为 2x,3x,… 这些数一定在 x 之前就被其他数的倍数标记过了，
                例如 2 的所有倍数，3 的所有倍数等。*/
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }
}
