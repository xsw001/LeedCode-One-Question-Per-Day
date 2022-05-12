package xsw.March.美团面试;
/*
小美负责维护某人民公园内的所有银杏树。

公园里有 n 棵银杏，编号为 1…n，第i棵树的高度为 hi。但是树木太多，她自己一个人肯定无法完成任务，于是她找了一些同学，并将他们分为两组。

为了公平，小美要确定两组的工作量（总爬树高度）一样多，而且小美也要参与工作。于是小美这样安排：自己先选一棵树 x 进行修剪，编号为 [x + 1, n] 的树重新编号为 [x，n - 1]，[1，x - 1] 的部分编号不变。对于新的编号，奇数编号的树由一队同学修剪，偶数编号的树由另一队同学修剪。

请帮小美计算，自己选择可以选择修剪哪些树才能让两组同学的工作量（总爬树高度）一样多。并输出方案数。

输入描述
第一行一个正整数 n，表示一共有 n 棵树；

第二行 n 个正整数 hi，第 i 个正整数表示第 i 棵树高度为 hi。

1 ≤ n ≤ 2×105, 1 ≤ hi ≤ 104。

输出描述
如果无论小美怎么选择，都没有一种方案可以使得两组工作量相同，则只输出一行一个数  0。

否则输出两行，第一行输出一个正整数表示小美选择树的方案数，第二行从小到大输出小美可以选择修剪树的编号，每两个编号之间有一个空格。请不要输出行末空格。


样例输入
4
1 4 7 3
样例输出
1
3
*/

import java.util.ArrayList;
import java.util.Scanner;

public class 小美爬树 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int total = 0;
        int even = 0,odd = 0;
        for (int i = 0; i < n; i++) {
            int a = cin.nextInt();
            list.add(a);
            total += a;
            if(i % 2 == 0)
                odd += a;
            else
                even += a;
        }
        for (int i = 0; i < n; i++) {
            if(i % 2 ==0){
                int newOdd = even - list.get(i);
                if (newOdd == odd)
                    res.add(i+1);
            }
        }
        int l = res.size();
        System.out.println(l);
        for (int i = 0; i < l -1; i++) {
            System.out.print(res.get(i)+" ");
        }
        System.out.print(res.get(l-1));
    }
}
/*
10 7 3
FFFFFTFFFF
*/
