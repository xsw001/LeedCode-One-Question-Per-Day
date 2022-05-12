package xsw.November21;
/*
866. 回文素数
求出大于或等于 N 的最小回文素数。

回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。

例如，2，3，5，7，11 以及 13 是素数。

回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。

例如，12321 是回文数。



示例 1：

输入：6
输出：7
示例 2：

输入：8
输出：11
示例 3：

输入：13
输出：101


提示：

1 <= N <= 10^8
答案肯定存在，且小于 2 * 10^8。




通过次数7,728提交次数34,400
 */

import java.util.ArrayList;

public class 回文素数_866 {

    public int primePalindrome(int n) {
        if (n == 1)
            return 2;

        while (n < 200000000) {
            if (isPalindrome(n) && isPrime(n))
                return n;
            ++n;
            if (10_000_000 < n && n < 100_000_000)
                n = 100_000_000;
        }
        return n;
    }

    boolean isPrime(int num) {
        //两个较小数另外处理
        if (num == 2 || num == 3)
            return true;
        //不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5)
            return false;
        int tmp = (int) Math.sqrt(num);
        //在6的倍数两侧的也可能不是质数
        for (int i = 5; i <= tmp; i += 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        //排除所有，剩余的是质数
        return true;
    }

    boolean isPalindrome(int num) {
        String s = "" + num;
        int l = 0, r = s.length() - 1;
        while (l < r)
            if (s.charAt(l++) != s.charAt(r--))
                return false;
        return true;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}