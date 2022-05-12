package xsw.October;
/*
638. 大礼包
在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。

给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。

还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。

返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。



示例 1：

输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
输出：14
解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。
示例 2：

输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
输出：11
解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。


提示：

n == price.length
n == needs.length
1 <= n <= 6
0 <= price[i] <= 10
0 <= needs[i] <= 10
1 <= special.length <= 100
special[i].length == n + 1
0 <= special[i][j] <= 50
通过次数12,857提交次数21,430
 */

import java.util.*;

public class 大礼包_638 {

    class Solution {

        Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();

        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            int n = price.size();

            // 过滤不需要计算的大礼包，只保留需要计算的大礼包
            List<List<Integer>> filterSpecial = new ArrayList<List<Integer>>();
            for (List<Integer> sp : special) {
                int totalCount = 0, totalPrice = 0;
                for (int i = 0; i < n; ++i) {
                    totalCount += sp.get(i);
                    totalPrice += sp.get(i) * price.get(i);
                }
                if (totalCount > 0 && totalPrice > sp.get(n)) {
                    filterSpecial.add(sp);
                }
            }

            return dfs(price, needs, filterSpecial, n);
        }

        // 记忆化搜索计算满足购物清单所需花费的最低价格
        public int dfs(List<Integer> price, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
            if (!memo.containsKey(curNeeds)) {
                int minPrice = 0;
                // 将 minPrice 初始化为原价购买购物清单 curNeeds 中的所有物品的花费；
                for (int i = 0; i < n; ++i) {
                    minPrice += curNeeds.get(i) * price.get(i); // 不购买任何大礼包，原价购买购物清单中的所有物品
                }
                // 逐个遍历所有可以购买的大礼包，不妨设当前遍历的大礼包为 curSpecial，其价格为 specialPrice
                for (List<Integer> curSpecial : filterSpecial) {
                    int specialPrice = curSpecial.get(n);
                    //计算购买大礼包 curSpecial 后新的购物清单 nxtNeeds
                    List<Integer> nxtNeeds = new ArrayList<Integer>();
                    for (int i = 0; i < n; ++i) {
                        if (curSpecial.get(i) > curNeeds.get(i)) { // 不能购买超出购物清单指定数量的物品
                            break;
                        }
                        nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                    }
                    if (nxtNeeds.size() == n) { // 大礼包可以购买
                        // 递归地计算满足购物清单 nxtNeeds 所需花费的最低价格 nxtPrice
                        int nxtPrice = dfs(price, nxtNeeds, filterSpecial, n);
                        // 计算满足当前购物清单 curNeeds 所需花费的最低价格 curPrice
                        int curPrice = nxtPrice + specialPrice;
                        // 如果 curPrice < minPrice，则将 minPrice 更新为 curPrice
                        minPrice = Math.min(minPrice, curPrice);
                    }
                }
                memo.put(curNeeds, minPrice);
            }
            //返回计算满足购物清单 curNeeds 所需花费的最低价格 minPrice
            return memo.get(curNeeds);
        }
    }

/*

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/shopping-offers/solution/da-li-bao-by-leetcode-solution-p1ww/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    class Solution1 {
        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            int n = price.size();
            int[] g = new int[n + 1];
            g[0] = 1;
            for (int i = 1; i <= n; i++) {
                g[i] = g[i - 1] * (needs.get(i - 1) + 1);
            }
            int mask = g[n];
            int[] f = new int[mask];
            int[] cnt = new int[n];
            for (int state = 1; state < mask; state++) {
                f[state] = 0x3f3f3f3f;
                Arrays.fill(cnt, 0);
                for (int i = 0; i < n; i++) {
                    cnt[i] = state % g[i + 1] / g[i];
                }
                for (int i = 0; i < n; i++) {
                    if (cnt[i] > 0) f[state] = Math.min(f[state], f[state - g[i]] + price.get(i));
                }
                out:
                for (List<Integer> x : special) {
                    int cur = state;
                    for (int i = 0; i < n; i++) {
                        if (cnt[i] < x.get(i)) continue out;
                        cur -= x.get(i) * g[i];
                    }
                    f[state] = Math.min(f[state], f[cur] + x.get(n));
                }
            }
            return f[mask - 1];
        }
    }
/*
    作者：AC_OIer
    链接：https://leetcode-cn.com/problems/shopping-offers/solution/gong-shui-san-xie-yi-ti-shuang-jie-zhuan-qgk1/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}