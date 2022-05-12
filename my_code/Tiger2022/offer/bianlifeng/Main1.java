package Tiger2022.offer.bianlifeng;

import java.util.*;

/*
便利蜂货架最多放多少件商品2
时间限制： 3000MS
内存限制： 539648KB
题目描述：
小明是便利蜂便利店的一名店员，今天准备将一批商品放到某一个货架上，货架空间资源是Y（单位：m^3）。
要放置的有N种商品，这些商品可能该货架放不下，也可能商品此时没货。现在还知道这N种商品的各自占用空间资源P（单位：m^3）和各自数量NUM（单位：件）。

小明想请教聪明的你，该货架最多可以摆多少件商品？（为简化问题，不需要考虑放置物品空隙,层次等浪费空间的情况）



输入描述
第一行是两个数字Y和N，之间用一个空格隔开，Y代表货架空间资源，N代表商品的种类数。

    接下来N行，每行两个数P和NUM，之间用一个空格隔开，P代表该商品占用的空间资源，NUM代表该商品的总共件数。

    其中 0 <= Y < 2147483647;

            0 <= N < 10^3;

            0 <  P < 10^4;

            0 <= NUM < 10^4，

    并且 Y, N, P, NUM均为整数。

输出描述
一个整数RES，代表最多可以摆放商品的数量。


样例输入
5 2
4 1
1 0
样例输出
1
 */
class Main1 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();
        int n = sc.nextInt();
        ArrayList<int[]> list = new ArrayList<>();
        int p, num;
        for (int i = 0; i < n; i++) {
            p = sc.nextInt();
            num = sc.nextInt();
            if (num > 0)
                list.add(new int[]{p, num});
        }
        list.sort(Comparator.comparingInt(o -> o[0]));
        int ans = 0;
        int idx = 0;
        while (sum > 0 && idx < list.size()) {
            int[] arr = list.get(idx++);
            if (sum < arr[0])
                break;
            for (int i = 0; i < arr[1]; i++) {
                if (sum >= arr[0]) {
                    sum -= arr[0];
                    ++ans;
                } else {
                    System.out.println(ans);
                    return;
                }
            }
        }
        System.out.println(ans);
    }
}