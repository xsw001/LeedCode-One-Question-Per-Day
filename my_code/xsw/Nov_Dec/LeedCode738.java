package xsw.Nov_Dec;/*
738. 单调递增的数字
给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。

（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

示例 1:

输入: N = 10
输出: 9
示例 2:

输入: N = 1234
输出: 1234
示例 3:

输入: N = 332
输出: 299
说明: N 是在 [0, 10^9] 范围内的一个整数
*/


public class LeedCode738 {

    public static int monotoneIncreasingDigits1(int N) {
/*      //肯定超时
        while (!isIncreasingDigits(N))
            --N;
        return N;*/
        if (isIncreasingDigits(N)) return N;
        int topBase = 1;
        while (N > 10) {
            if (isIncreasingDigits(N - 1))//这里可以优化成N-1,因为N*topBase-1后面都是0，减了以后变9，后面必然递增，要看的是N-1
                return N * topBase - 1;
            N = N / 10;
            topBase *= 10;
        }
        return topBase * N - 1;
    }

    public static boolean isIncreasingDigits(int num) {
        while (num != 0) {
            int a = num % 10;
            num /= 10;
            int b = num % 10;
            if (b > a)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int digits = monotoneIncreasingDigits1(3342132);
        System.out.println(digits);
    }

    //找到不满足单调递增的标记位后，后面的数全变为‘9’。
    public static int monotoneIncreasingDigits2(int N) {
        char[] chars = String.valueOf(N).toCharArray();
        int length = chars.length;
        for (int i = length - 1; i > 0; i--) {
            if (chars[i] < chars[i - 1]) {
                chars[i - 1]--;
                for (int j = i; j < length; j++) {
                    if (chars[j] == '9')
                        break;
                    chars[j] = '9';
                }
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    //假设不考虑 N 的限制，那么对于一个长度为 n 的数字，最大单调递增的数字一定是每一位都为 9 的数字。
    public static int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        //如果找到第一个位置 i 使得 [0,i-1] 的数位单调递增且 strN[i−1]>strN[i]
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }//此时 [0,i] 的数位都与 N 的对应数位相等，仍然被 N 限制着
        //为了得到最大的数字，我们需要解除 N 的限制，来让剩余的低位全部变成 9 ，即能得到小于 N 的最大整数
        //故尝试让 strN[i−1] 自身数位减 1。此时已经不再受 N 的限制，直接将 [i, n-1] 的位置上的数全部变为 9
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            //当 strN[i−1] 自身数位减 1 后可能会使得 strN[i−1] 和 strN[i−2] 不再满足递增的关系
            //因此我们需要从 i-1 开始递减比较相邻数位的关系
            //直到找到第一个位置 j 使得 strN[j] 自身数位减 1 后 strN[j−1] 和 strN[j] 仍然保持递增关系
            //或者位置 j 已经到最左边（即 j 的值为 0）
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
