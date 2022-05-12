package Tiger2022.offer.bianlifeng;

import java.util.*;

/*
购物套餐 2
时间限制： 3000MS
内存限制： 589824KB
题目描述：
在便利蜂某门店中，有n种“蜂智选”商品，这些商品价格不一。在特殊时候，门店也会推出促销活动，以组合优惠的方式出售这些商品。

比如，假设有3种商品：A、B、C，价格分别为 1、2、1，现在有套餐：2个A 、1个B、1个C，这个组合的优惠价格是4。

而如果你的购物清单上有2个A、2个B、2个C，那么最划算的买法就是（1个套餐 + 单买一个B + 单买1个C）总价7。

当然，像这样的优惠套餐可能不止一个。

我们的目标是，根据给定的套餐和购物计划，找到花费最小的购买方案，输出总花费。

数据范围

1 <= n <= 10
0 < price[i] <= 10
0 <= purchase[i] <= 10
1 <= combine.length <= 100
0 <= combine[i][j] <= 20   (j < n，套餐中各个商品的数量)
1 <= combine[i][j] <= 300 (j = n，套餐价格)



输入描述
第1行：整数n，代表有n种“蜂智选”商品；

第2行：输入一个数组price，数组长度为n，price[i]表示第i种“蜂智选”商品的价格，；

第3行：输入一个数组purchase，数组长度为n，purchase[i]表示第i种蜂智选商品的采购量；

第4行：输入一个整数m，代表有m种套餐；

第5到m+5行：表示套餐数组combine[m][n+1]；m行数据表示有m种套餐，每行长度为 n + 1，每行的第 i 个数表示该套餐中第 i 种蜂智选商品的个数；
特殊的：每一行的第 n+1 个（最后一个）数表示当前套餐的价格；



请计算满足采购清单purchase所需花费的最低价格。 附：必须严格按照购物清单采购，不可多买/少买；

输出描述
输出一个最优结果：满足购物清单的最小花费。


样例输入
3
1 2 3
1 1 1
1
1 1 1 4
样例输出
4

提示
样例说明：购买方案：购买第一个特惠套餐就满足购物清单的需求，花费为4。其他任意方法在满足题目要求的情况下，都没办法达到更优的价格。

附加样例：

输入：

3
4 2 6    //表示有三件商品，价格分别为4、2、6
1 2 1    //购物清单里有1个商品A、2个商品B、1个商品C
2
1 2 0 6
0 2 1 7    //有两个特惠套餐：（包含1个商品A、2个商品B、0个商品C；总价格6）（包含0个商品A、2个商品B、1个商品C；总价格7）
输出：
11    //输出购买套餐/单买/组合购买最划算的价格：11


购买方案：购买第二个特惠套餐与单独购买商品A可满足购物清单的需求，花费为11。其他任意方法在满足题目要求的情况下，都没办法达到更优的价格。

 */
class Main2 {

    static int min;

    public static void main(String args[]) {
        min = 0;
        Scanner sc = new Scanner(System.in);
        //第1行：整数n，代表有n种“蜂智选”商品；
        int n = sc.nextInt();
        //第2行：输入一个数组price，数组长度为n，price[i]表示第i种“蜂智选”商品的价格，；
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        //第3行：输入一个数组purchase，数组长度为n，purchase[i]表示第i种蜂智选商品的采购量；
        int[] purchases = new int[n];
        for (int i = 0; i < n; i++) {
            purchases[i] = sc.nextInt();
            min += prices[i] * purchases[i];
        }
        //第4行：输入一个整数m，代表有m种套餐；
        int m = sc.nextInt();
        //第5到m+5行：表示套餐数组combine[m][n+1]；m行数据表示有m种套餐，每行长度为 n + 1
        // 每行的第 i 个数表示该套餐中第 i 种蜂智选商品的个数；特殊的：每一行的第 n+1 个（最后一个）数表示当前套餐的价格；
        int[][] combine = new int[m][n + 1];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            boolean f = false;
            for (int j = 0; j < n + 1; j++) {
                combine[i][j] = sc.nextInt();
                if (!f && j < n && combine[i][j] > purchases[j])
                    f = true;
            }
            if (f)
                set.add(i);
        }

        back(prices, purchases, combine, set, 0, min);

        System.out.println(min);

    }

    private static void back(int[] prices, int[] purchases, int[][] combine, HashSet<Integer> set, int start, int cur) {
        if (start == combine.length)
            return;
        min = Math.min(min, cur);
        for (int purchase : purchases) {
            if (purchase <= 0)
                return;
        }
        for (int i = start; i < combine.length; i++) {
            if (set.contains(i))
                continue;
            boolean f = false;
            int temp = 0;
            int pre = cur;
            int[] aaa = Arrays.copyOfRange(purchases, 0, purchases.length);
            for (int j = 0; j < purchases.length; j++) {
                temp += combine[i][j] * prices[j];
                if (purchases[j] < combine[i][j])
                    f = true;
                purchases[j] -= combine[i][j];
            }
            if (f) {
                purchases = aaa;
                continue;
            }
            cur = cur - temp + combine[i][prices.length];
            back(prices, purchases, combine, set, i, cur);
            cur = pre;
            purchases = aaa;
        }
    }
}