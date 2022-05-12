package xsw.April;
/*
403. 青蛙过河
一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。

给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。

开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。

如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。



示例 1：

输入：stones = [0,1,3,5,6,8,12,17]
输出：true
解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
示例 2：

输入：stones = [0,1,2,3,4,8,9,11]
输出：false
解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。


提示：

2 <= stones.length <= 2000
0 <= stones[i] <= 231 - 1
stones[0] == 0
通过次数16,511提交次数40,179
 */

import java.util.HashSet;
import java.util.HashMap;

public class hard_403 {

    /*执行用时：93 ms, 在所有 Java 提交中击败了14.57%的用户*/
    public static boolean canCross1(int[] stones) {
        if (stones[1] != 1)
            return false;
        int l = stones.length;
        for (int i = 1; i < l; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        HashSet<Integer> list = new HashSet<>();
        list.add(0);
        map.put(0, list);
        for (int i = 1; i < l; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (int j = i - 1; j >= 0; j--) {
                int high = stones[i] - stones[j];
                if(high > j+1) //这个判断顶下边的两个
                    break;
                HashSet<Integer> diff = map.get(j);
                int max = 0;
                for (Integer d : diff) {
                    max = Math.max(max, d);
                    if (d != -1 && Math.abs(d - high) < 2)
                        temp.add(high);
                }
/*                if (temp.isEmpty())
                    continue;          //可以省略，但是用时会增加，测试用例的原因
                if ((temp.iterator().next() != -1) && max < high - 1)
                    break;*/
            }
            if (temp.isEmpty()) {
                temp.add(-1);
            }
            map.put(i, temp);
        }
        System.out.println(map);
/*        HashSet<Integer> set = map.get(l - 1);
        for (Integer integer : set) {
            if (integer == -1)
                return false;
        }*/
        return map.get(l - 1).iterator().next() != -1;
    }

    public static void main(String[] args) {
        int[] data = {0, 1, 3, 6, 10, 15, 16, 21};
        System.out.println(canCross1(data));
        System.out.println(canCross(data));
    }

    //「状态定义」所代表的含义：当前在第 i 个位置，并且是以步长 k 跳到位置 i 时，是否到达最后一块石子。
    public static boolean canCross(int[] ss) {
        int n = ss.length;
        // check first step
        if (ss[1] != 1) return false;
        /*第一维为可变参数 u，代表石子列表的下标，范围为数组 stones 长度；
第二维为可变参数 k，代表上一步的的跳跃步长，最多不超过数组 stones 长度。*/
        boolean[][] f = new boolean[n][n];
        f[1][1] = true;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                int k = ss[i] - ss[j];
                if (k <= j + 1) {
/*那么对于 f[i][k] 是否为真，则取决于上一位置 j 的状态值，结合每次步长的变化为 [-1,0,1] 可知：
    可从 f[j][k−1] 状态而来：先是经过 k−1 的跳跃到达位置 j，再在原步长的基础上 +1，跳到了位置 i。
    可从 f[j][k] 状态而来：先是经过 k 的跳跃到达位置 j，维持原步长不变，跳到了位置 i。
    可从 f[j][k+1] 状态而来：先是经过 k+1 的跳跃到达位置 j，再在原步长的基础上 -1，跳到了位置 i。
    只要上述三种情况其中一种为真，则 f[i][j] 为真。
*/
                    f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                    if (i == n - 1 && f[i][k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}