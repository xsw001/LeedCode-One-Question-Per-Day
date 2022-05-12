package xsw.April;
/*
1006. 笨阶乘
通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。

相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。

例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。

另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。

实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。



示例 1：

输入：4
输出：7
解释：7 = 4 * 3 / 2 + 1
示例 2：

输入：10
输出：12
解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1


提示：

1 <= N <= 10000
-2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
通过次数7,694提交次数13,422
 */

import java.util.*;

public class medium_1006 {

    //执行用时: 8 ms
    //内存消耗: 37.5 MB
    //18%
    public static int clumsy1(int N) {
        ArrayList<Integer> nums = new ArrayList<>();
        int n = N;
        while (n > 3) {
            nums.add(n * (n - 1) / (n - 2));
            nums.add(n - 3);
            n -= 4;
        }
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i == 0) {
                res = nums.get(0);
                continue;
            }
            if (i % 2 != 0)
                res += nums.get(i);
            else {
                res -= nums.get(i);
            }
        }
        if (n == 1)
            res--;
        else if (n == 2)
            res -= 2;
        else if (n == 3)
            res -= 6;
        return Math.abs(res);
    }

    //执行用时: 1 ms
    //内存消耗: 35 MB
    //%82
    public static void main(String[] args) {
        System.out.println(clumsy(10));
        for (int i = 1; i <= 100; i++) {
            System.out.println(clumsy(i));
        }
    }

    public static int clumsy(int N) {
        if(N < 3)
            return N;
        int res = N * (N - 1) / (N - 2) + (N - 3);
        int n = N-4;
        while (n > 3) {
            res -= n * (n - 1) / (n - 2) - (n - 3);
            n -= 4;
        }
        if (n == 1)
            res--;
        else if (n == 2)
            res -= 2;
        else if (n == 3)
            res -= 6;
        return res;
    }

    //你好，四月
    //愚人节
    public int clumsy2(int N) {
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 6;
        } else if (N == 4) {
            return 7;
        }

        if (N % 4 == 0) {
            return N + 1;
        } else if (N % 4 <= 2) {
            return N + 2;
        } else {
            return N - 1;
        }
    }
}