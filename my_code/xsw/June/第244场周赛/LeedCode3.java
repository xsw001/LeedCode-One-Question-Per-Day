package xsw.June.第244场周赛;
/*
5778. 使二进制字符串字符交替的最少反转次数 显示英文描述
通过的用户数465
尝试过的用户数1550
用户总通过次数483
用户总提交次数3434
题目难度Medium
给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：

类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。

我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。

比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。


示例 1：

输入：s = "111000"
输出：2
解释：执行第一种操作两次，得到 s = "100011" 。
然后对第三个和第六个字符执行第二种操作，得到 s = "101010" 。
示例 2：

输入：s = "010"
输出：0
解释：字符串已经是交替的。
示例 3：

输入：s = "1110"
输出：1
解释：对第二个字符执行第二种操作，得到 s = "1010" 。


提示：

1 <= s.length <= 105
s[i] 要么是 '0' ，要么是 '1' 。
 */

import java.util.*;

public class LeedCode3 {

    // 超时
    public static int minFlips(String s) {
        int l = s.length();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i <= l / 2; i++) {
            s1.append("10");
            s2.append("01");
        }
        if (s1.length() > l)
            s1.deleteCharAt(l);
        if (s2.length() > l)
            s2.deleteCharAt(l);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            int t = Math.min(cha(s1, s), cha(s2, s));
            res = Math.min(res, t);
            s = s.substring(1, l) + s.charAt(0);
        }
        return res;
    }

    private static int cha(StringBuilder s1, String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s1.charAt(i) != s.charAt(i)) ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        System.out.println(minFlips1("1010110"));
    }

    /*思路
类型 1 的操作，其实是头尾相接
将字符串复制一份接在后面，即可使用滑动窗口丝滑拼接
滑窗时减去离开的格子，加上进来的格子，即可避免大量重复计算
按照 01 检测，需要修改的次数，用 len 减去就是按照 10 检测的次数*/
    public static  int minFlips1(String s) {
        String target = "01";
        int count = 0;
        int len = s.length();
        // 计算第一个滑窗
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != target.charAt(i % 2)) {
                count++;
            }
        }
        int ans = Math.min(count, len - count);
        s += s;
        // 滑窗
        for (int i = 0; i < len; i++) {
            //到i为止的要放在后面，并且从01转为10
            //求到i为止的01排列
            if (s.charAt(i) != target.charAt(i % 2)) {
                count--;
            }
            //[i + 1, n - 1] 01排列, [0, i] 10排列
            //此时的cc是01排列了
            if (s.charAt(i + len) != target.charAt((i + len) % 2)) {
                count++;
            }
            ans = Math.min(ans, Math.min(count, len - count));
        }
        return ans;
    }
}