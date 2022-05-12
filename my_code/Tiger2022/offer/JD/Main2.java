package Tiger2022.offer.JD;

import java.util.HashMap;
import java.util.Scanner;

/*
小红与粉刷匠
时间限制： 5000MS
内存限制： 589824KB
题目描述：
      小红遇到了一名粉刷匠。这名粉刷匠有三种颜料，分别是红、黄、蓝。为了方便， 这三种颜料分别命名为 A，B，C。

      现在，粉刷匠正在粉刷一面长度为3n 的墙壁。粉刷完之后，三种颜料的数目都相同。由于小红一不小心踢到了颜料桶，使得这面墙的每个地方都被染上了三种颜料中的其中一种，这很让粉刷匠头疼。

       粉刷匠每次可以选择一段连续的墙壁进行粉刷，即全部粉刷上同一种颜色（A,B,C 三种中的其中一种）。粉刷匠想知道，他最少需要多少次粉刷才能使得三种颜料的数目都相同？



输入描述
第一行一个正整数 T ，表示有 T 组数据。

对于每一组数据，第一行输入一个正整数 n，表示墙壁的长度为 3n；第二行一个长度为 3n 的字符串，仅包含 ABC 三种字母，表示该墙壁的初始颜色。

1 ≤ n ≤ 5 · 104, 1 ≤ T ≤ 5。

输出描述
对于每一组数据，输出一个整数，表示答案。


样例输入
3
2
ABACBC
3
AAABBBBAC
3
CAACBCBCC
样例输出
0
1
2

提示
对于第一组样例，A,B,C 的数目都是 2，故无需进行粉刷；

对于第二组样例，选择区间 [7, 9]  全部粉刷为 C，之后形成的墙壁为 AAABBBCCC；

对于第三组样例，先选择区间 [4, 4] 粉刷为 B 之后形成的墙壁为 CAABBCBCC，
再选择区间 [9, 9] 全部粉刷为 A 之后形成的墙壁为 CAABBCBCA。此时每种颜色都有 3 个。
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            String s = sc.next();
            System.out.println(help(s, n));
        }
    }

    private static int help(String s, int n) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') ++a;
            else if (s.charAt(i) == 'B') ++b;
            else ++c;
        }
        if (a == b && b == c)
            return 0;
        int o = Math.min(a, Math.min(b, c));
        int t = 0;
        if (o == a) {
            t = n * 3 - a - Math.min(b, c);
        } else if (o == b) {
            t = n * 3 - b - Math.min(a, c);
        } else
            t = n * 3 - c - Math.min(a, b);

        return t;
    }
}

