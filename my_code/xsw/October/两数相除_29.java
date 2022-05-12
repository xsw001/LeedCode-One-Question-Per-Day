package xsw.October;
/*
29. 两数相除
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2



示例 1:

输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2


提示：

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
通过次数113,165提交次数541,034
 */

import java.util.ArrayList;

public class 两数相除_29 {

    /**
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     * <p>
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     * <p>
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     * <p>
     * 我们可以以100/3为例
     * <p>
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     * <p>
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     * <p>
     * 所以一共是减去了33个3，所以商就是33
     * <p>
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     */
    public static int divide1(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) < 0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((t >> i) >= d) {//找出足够大的数2^n*divisor
                result += 1 << i;//将结果加上2^n
                t -= d << i;//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        int a = 7;
        int b = -3;
        System.out.println(divide1(a, b));
        System.out.println(Solution.divide(a, b));
    }

    static class Solution {
        public static int divide(int dividend, int divisor) {
            // 考虑被除数为最小值的情况
            if (dividend == Integer.MIN_VALUE) {
                if (divisor == 1) {
                    return Integer.MIN_VALUE;
                }
                if (divisor == -1) {
                    return Integer.MAX_VALUE;
                }
            }
            // 考虑除数为最小值的情况
            if (divisor == Integer.MIN_VALUE) {
                return dividend == Integer.MIN_VALUE ? 1 : 0;
            }
            // 考虑被除数为 0 的情况
            if (dividend == 0) {
                return 0;
            }

            // 一般情况，使用二分查找
            // 将所有的正数取相反数，这样就只需要考虑一种情况
            boolean rev = false;
            if (dividend > 0) {
                dividend = -dividend;
                rev = !rev;
            }
            if (divisor > 0) {
                divisor = -divisor;
                rev = !rev;
            }

            int left = 1, right = Integer.MAX_VALUE, ans = 0;
            while (left <= right) {
                // 注意溢出，并且不能使用除法
                int mid = left + ((right - left) >> 1);
                boolean check = quickAdd(divisor, mid, dividend);
                if (check) {
                    ans = mid;
                    // 注意溢出
                    if (mid == Integer.MAX_VALUE) {
                        break;
                    }
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return rev ? -ans : ans;
        }

        // 快速乘
        public static boolean quickAdd(int y, int z, int x) {
            // x 和 y 是负数，z 是正数
            // 需要判断 z * y >= x 是否成立
            int result = 0, add = y;
            while (z != 0) {
                if ((z & 1) != 0) {
                    // 需要保证 result + add >= x
                    if (result < x - add) {
                        return false;
                    }
                    result += add;
                }
                if (z != 1) {
                    // 需要保证 add + add >= x
                    if (add < x - add) {
                        return false;
                    }
                    add += add;
                }
                // 不能使用除法
                z >>= 1;
            }
            return true;
        }
    }

/*    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/divide-two-integers/solution/liang-shu-xiang-chu-by-leetcode-solution-5hic/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}