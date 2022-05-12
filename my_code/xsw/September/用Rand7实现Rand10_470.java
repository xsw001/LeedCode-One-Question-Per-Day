package xsw.September;
/*
470. 用 Rand7() 实现 Rand10()
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。



示例 1:

输入: 1
输出: [7]
示例 2:

输入: 2
输出: [8,4]
示例 3:

输入: 3
输出: [8,1,10]


提示:

rand7 已定义。
传入参数: n 表示 rand10 的调用次数。


进阶:

rand7()调用次数的 期望值 是多少 ?
你能否尽量少调用 rand7() ?
通过次数37,823提交次数68,434
 */

import java.util.ArrayList;
import java.util.Random;

public class 用Rand7实现Rand10_470 {

    /*
        rand7 可生成 1 到 7 范围内的均匀随机整数
        (rand7() - 1) * 7 + rand7()可生成 1 到 49 范围内的均匀随机整数
        (a - 1) * b + randb() 可生成 1 到 ab 范围内的均匀随机整数
     */
    public static int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            // 如果在40以内，那就直接返回
            if (num <= 40) return 1 + num % 10;
            // 说明刚才生成的在41-49之间，利用随机数再操作一遍
            num = (num - 40 - 1) * 7 + rand7();
            if (num <= 60) return 1 + num % 10;
            // 说明刚才生成的在61-63之间，利用随机数再操作一遍
            num = (num - 60 - 1) * 7 + rand7();
            if (num <= 20) return 1 + num % 10;

        }
//
//        作者：jerry_nju
//        链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/xiang-xi-si-lu-ji-you-hua-si-lu-fen-xi-zhu-xing-ji/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    private static int rand7() {
        return (int) (Math.random() * 7 + 1);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            System.out.println(rand10());
    }

}