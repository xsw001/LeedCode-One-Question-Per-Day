package xsw.June;
/*
1049. 最后一块石头的重量 II
有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。

每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。



示例 1：

输入：stones = [2,7,4,1,8,1]
输出：1
解释：
组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
示例 2：

输入：stones = [31,26,33,21,40]
输出：5
示例 3：

输入：stones = [1,2]
输出：1


提示：

1 <= stones.length <= 30
1 <= stones[i] <= 100
通过次数14,178提交次数25,477
 */
/*
 * 和昨天的一样。494题
 * 转换成01背包问题
 * 所有石头的和为sum，两两抵消后 ---> 相当于每个石头前面都有+或-符号，就是把石头分成了两部分，一部分正，一部分负
 * 正 + 负 = sum
 * 求： min（正 - 负）
 * 所以呢，正 ~ 负 时最小，最大值为 sum/2
 * 背包最多装sum / 2的石头,stones数组里有一大堆石头。求如何装能装下最多重量石头。
 * */

import java.util.ArrayList;

public class 最后一块石头的重量II_1049 {

    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int v = sum / 2;
        int[] dp = new int[v+1];
        for (int stone : stones) {
            for (int j = v; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return  sum - dp[v] * 2;
    }

    public static void main(String[] args) {
        int[] data = {2,1};
        System.out.println(lastStoneWeightII(data));

    }

}