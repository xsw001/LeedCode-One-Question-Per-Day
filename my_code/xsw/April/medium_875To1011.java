package xsw.April;
/*
875. 爱吃香蕉的珂珂
珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。



示例 1：

输入: piles = [3,6,7,11], H = 8
输出: 4
示例 2：

输入: piles = [30,11,23,4,20], H = 5
输出: 30
示例 3：

输入: piles = [30,11,23,4,20], H = 6
输出: 23


提示：

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9
通过次数30,498提交次数65,127
 */

import java.util.ArrayList;
import java.util.Arrays;

public class medium_875To1011 {

    /*执行用时：26 ms, 在所有 Java 提交中击败了17.04%的用户*/
    public static int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        while (l < r) {
            int mid = (l + r) / 2;
            int count = 0;
            for (int pile : piles) {
/*                if (pile <= mid) {
                    ++count;
                } else {
                    count += pile % mid == 0 ? pile / mid : pile / mid + 1;
                }*/
                count += (pile - 1) / mid + 1;
                //count += (pile + mid - 1) / mid;
            }
            //如果天数超了, 说明运载能力有待提升, l 改大一点, 继续二分搜索
            if (count > h)
                l = mid + 1;
            else//否则运载能力改小一点继续搜索
                r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] data = {312884470};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(minEatingSpeed(data, 312884469));

    }

}