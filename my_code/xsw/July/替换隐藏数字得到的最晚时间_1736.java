package xsw.July;
/*
1736. 替换隐藏数字得到的最晚时间
给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。

有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。

替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。



示例 1：

输入：time = "2?:?0"
输出："23:50"
解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
示例 2：

输入：time = "0?:3?"
输出："09:39"
示例 3：

输入：time = "1?:22"
输出："19:22"


提示：

time 的格式为 hh:mm
题目数据保证你可以由输入的字符串生成有效的时间
通过次数10,397提交次数24,268
 */

import java.util.ArrayList;

public class 替换隐藏数字得到的最晚时间_1736 {

    public static String maximumTime(String time) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) != '?')
                ans.append(time.charAt(i));
            else {
                if (i == 0) {
                    if (time.charAt(1) != '?' && time.charAt(1) > '3')
                        ans.append(1);
                    else
                        ans.append(2);
                } else if (i == 1) {
                    if (ans.charAt(0) == '2')
                        ans.append(3);
                    else
                        ans.append(9);
                } else if (i == 3) {
                    ans.append(5);
                } else if (i == 4)
                    ans.append(9);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}