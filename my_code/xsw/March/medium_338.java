package xsw.March;
/*
338. 比特位计数
给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:

输入: 2
输出: [0,1,1]
示例 2:

输入: 5
输出: [0,1,1,2,1,2]

8
[0,1,1,2,1,2,2,3,1]
18
[0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2]
48
[0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2]
88
[0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3]
进阶:

给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
要求算法的空间复杂度为O(n)。
你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */

import java.util.ArrayList;
import java.util.Arrays;

public class medium_338 {

    public static int[] countBits1(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    private static int findOne(int i) {
        int res = 0;
        while (i != 0) {
            if (i % 2 == 1)
                ++res;
            i = i / 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(85)));
    }

    public static int[] countBits2(int num) {
        int[] res = new int[num + 1];
        res[1] = 1;
        for (int i = 2; i <= num; i++) {
            if (i % 2 == 1) {
                res[i] = res[i / 2] + 1;//x 除以 2 可以通过 x>>1 得到
            } else
                res[i] = res[i / 2];//x 除以 2 的余数可以通过 x&1 得到
        }
        return res;
    }

    public static int[] countBits(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            System.out.println(i & (i - 1));
        }
        return bits;
    }

}