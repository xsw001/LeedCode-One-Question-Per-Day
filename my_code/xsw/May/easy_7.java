package xsw.May;
/*
7. 整数反转
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。


示例 1：

输入：x = 123
输出：321
示例 2：

输入：x = -123
输出：-321
示例 3：

输入：x = 120
输出：21
示例 4：

输入：x = 0
输出：0


提示：

-231 <= x <= 231 - 1
通过次数692,707提交次数1,957,041
 */

import java.util.ArrayList;

public class easy_7 {
    public static int reverse(int x) {
        int res = 0;
        while(x != 0){
            if(res < -214748364 || res > 214748364)
                return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(reverse((-1234)));
    }

}