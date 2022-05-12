package xsw.March.美团面试;
/*
小美想要为小团摆一行积木，每个积木上都有一个0-9的数字。现在已经摆好了 n 块积木，小美可以把其中一块积木替换成任意一块积木（也可以不替换），使得积木看起来更符合小美的审美。请你帮小美看看，替换后最好看的积木是什么样的。

摆好后的积木上面的数字，从左到右会形成一个数字串（由数字组成的字符串）。小美会根据这个数字串来评判积木的好看程度，小美有两条审美标准：

①回文数字串相比于非回文数字串更符合小美的审美。例如：12321、2332是回文数字串，而12212、2121不是回文数字串。

②数字串形成的数字更小更好看。例如：1312比1313更好看，0102比1102更好看。

小美会按照她的审美标准来判断两个数字串哪个更好看，即先按照审美标准①判断，若无法判断再按审美标准②判断。

输入描述
第一行一个数 T，表示一共有 T 组测试数据。(1 ≤ T ≤ 100)。

接下来 T 组数据，每组数据两行，

第一行一个数 n，表示有 n 块积木。(1 ≤ n ≤ 20000)。

第二行 n 个数字，第 i 块积木上的数字是 si。(si是0-9的数字)。

输出描述
每组数据输出一行，表示最终摆好的积木形成的数字串。


样例输入
2
5
00011
5
11210
样例输出
00001
01210

提示
第一组数据：
替换一块积木，无法使数字串变成回文数字串，因此只能数字串形成的数字最小。
第二组数据：
可以把第一块积木1换成0，也可以把第五块积木0换成1，从而使得积木是回文积木。又想要积木字典序最小，所以把第一块积木1替换成0。
*/

import java.io.*;
import java.util.*;


public class 小美摆积木 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int count = cin.nextInt();
        for (int i = 0; i < count; i++) {
            int n = cin.nextInt();
            String str = cin.next();
            String res = help(n, str);
            System.out.println(res);
        }
    }

    private static String help(int len, String str) {
        String res = "";
        int diff = 0;
        int r = len - 1, l = 0;
        while (l <= r) {
            if (str.charAt(l) != str.charAt(r)) {
                ++diff;
                if (diff > 1) {
                    res = findMin(str);
                    break;
                }
                StringBuilder sb1 = new StringBuilder(str);
                String t1 = sb1.replace(l, l + 1, str.charAt(r) + "").toString();
                StringBuilder sb2 = new StringBuilder(str);
                String t2 = sb2.replace(r, r + 1, str.charAt(l) + "").toString();
                int i1 = Integer.parseInt(t1);
                int i2 = Integer.parseInt(t2);
                res = i1 < i2 ? t1 : t2;
            }
            --r;
            ++l;
        }
        if (diff == 0) {
            return str;
        }
        return res;
    }

    private static String findMin(String str) {

        char min = '0';
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(min > c)
                min = c;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(min != c) {
                str = str.replaceFirst(c + "", min + "");
                break;
            }
        }
        return str;
    }
}
/*

        int N, M;
        // 读取输入，直到没有整型数据可读
        while(cin.hasNextInt())
        {
            // 读取N 和 M
            N = cin.nextInt();
            M = cin.nextInt();
            System.out.println(String.format("%d %d", N, M));
            // 读取接下来M行
            for (int i=0; i<M; i++) {
                // 读取每行的a b c
                int a = cin.nextInt(),
                        b = cin.nextInt(),
                        c = cin.nextInt();
                System.out.println(String.format("%d %d %d", a, b, c));
            }
        }
  */