package Tiger2022.offer.bianlifeng;

import java.util.*;

/*
输入描述
第一行两个正整数n和Y，分别表示可供购买的商品数量和优惠券的最低使用总价。
接下来一行n个整数，第i个整数表示第i种商品的价格。[每个数之间用空格隔开]

数据范围：

1 <= n <= 200

0< Ai  <= 100

1 <= Y <= 10000

输出描述
共一行，1 个数，表示要使用这张满 Y 元减20 元的优惠券最少需要购买多少元的商品,保证有解。


样例输入
4 30
18 20 22 21
样例输出
38

提示
样例 2：

输入：
6 70
25 34 33 46 49 31
输出：
71
 */
class Main {
    static int ans;

    public static void main(String args[]) {
        ans = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int lowPrice = sc.nextInt();
        int[] prices = new int[num];
        for (int i = 0; i < num; i++) {
            prices[i] = sc.nextInt();
        }
        Arrays.sort(prices);
        help(prices, lowPrice, 0, 0);
        System.out.println(ans);
    }

    private static void help(int[] prices, int lowPrice, int totalPrice, int i) {
        if (totalPrice >= lowPrice) {
            ans = Math.min(ans, totalPrice);
            return;
        }
        if (i == prices.length || prices[i] > lowPrice)
            return;
        while (i < prices.length) {
            totalPrice += prices[i];
            help(prices, lowPrice, totalPrice, i + 1);
            totalPrice -= prices[i];
            ++i;
        }
    }
}