package Tiger2022.offer.meituan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
7的倍数
时间限制： 3000MS
内存限制： 589824KB
题目描述：
小美喜欢7的倍数。桌面上有一些卡片，每张卡片上都印有一个数字
小美想从中挑选一些卡片，使得卡片上的数字之和最大，由于小美很喜欢7的倍数
她同时还希望挑选出的卡片的数字之和是7的倍数，请问她能挑选出的最大数字之和是多少？（注意，小美也可以一张卡片都不挑选）



输入描述
第一行是一个正整数n，表示桌上有n张写有数字的卡片。

第二行有n个空格隔开的整数a1,a2,…,an，其中ai表示桌上第i张卡片上所写的数字。

1<=n<=50000, |ai|<=3000

输出描述
一行一个整数，表示小美能挑选出的最大数字之和（且和为7的倍数）。


样例输入
4
1 3 6 6
样例输出
7

提示
输入样例2

5

-2 -6 15 4 5

输出样例2

14
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> z = new ArrayList<>();
        ArrayList<Integer> f = new ArrayList<>();
        f.add(0);
        int max = 0;
        for (int i = 0; i < n; i++) {
            int anInt = sc.nextInt();
            if (anInt < 0) {
                int size = f.size();
                for (int j = 0; j < size; j++) {
                    f.add(f.get(j) + anInt);
                }
            } else if (anInt > 0) {
                z.add(anInt);
                max += anInt;
            }
        }
        Collections.sort(z);
        HashSet<Integer> set = new HashSet<>(f);
        int i = 0;
        while (max > 0) {
            if (max % 7 == 0) {
                System.out.println(max);
                return;
            }
            int t = max / 7 * 7;
            int res = max - t;
            if (set.contains(-res)) {
                System.out.println(t);
                return;
            }
            while ((max - z.get(i)) / 7 * 7 == t) {
                if ((max - z.get(i)) % 7 == 0) {
                    System.out.println(max - z.get(i));
                    return;
                }
                ++i;
            }
            max -= z.get(i);
        }
        System.out.println(0);
    }
}
