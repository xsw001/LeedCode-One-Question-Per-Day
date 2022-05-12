package Tiger2022.offer.Java360;

import java.util.HashMap;
import java.util.Scanner;

/*
堆积货物
时间限制： 3000MS
内存限制： 589824KB
题目描述：
在某个仓库中，堆积了很多的货物。每个货物是一个正方体的，边长为1。所有货物恰好堆积成一个长方体，边长为R*C*L。

目前，在某次确认货物的时候，管理员意识到这一堆货物被小偷偷走了一些。这个小偷将原来的R*C*L的货物组成的长方体偷成了恰好(R-2)*(C-1)*(L-2)的长方体。

但是管理员记不得的R,C,L具体数值了，他只能计算现在的货物总数。他希望算出来最坏情况下被偷了多少的货物，输出这个最坏的值。



输入描述
输入为一个数n,表示题面中的(R-2)*(C-1)*(L-2)

输出描述
输出为一个数，表示最坏情况下被偷了多少的货物


样例输入
4
样例输出
41

提示
对于100%的数据：1≤n≤10^9 样例解释：R=3,C=5,L=3,3*5*3-(3-2)*(5-1)*(3-2)=41
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long pow = (int) Math.pow(n, 1d / 3);
        long max = 0;
        for (int i = 1; i <= pow; i++) {
            if (n % i == 0) {
                long a = n / i;
                for (int j = 1; j < (int) Math.sqrt(a); j++) {
                    if (a % j == 0) {
                        long k = a / j;
                        max = Math.max(max, help(i, j, k));
                    }
                }
            }
        }
        System.out.println(max - n);
    }

    private static long help(long i, long j, long k) {
        long max = 0;
        max = Math.max(max, count(i, j, k));
        max = Math.max(max, count(i, k, j));
        max = Math.max(max, count(k, i, j));
        max = Math.max(max, count(k, j, i));
        max = Math.max(max, count(j, i, k));
        max = Math.max(max, count(j, k, i));
        return max;
    }

    private static long count(long i, long j, long k) {
        return (i + 2) * (j + 1) * (k + 2);
    }
}
