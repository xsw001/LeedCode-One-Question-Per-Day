package xsw.Nov_Dec;

/*
947. 移除最多的同行或同列石头
n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。

如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。

给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。



示例 1：

输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
输出：5
解释：一种移除 5 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
示例 2：

输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
输出：3
解释：一种移除 3 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
示例 3：

输入：stones = [[0,0]]
输出：0
解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。


提示：

1 <= stones.length <= 1000
0 <= xi, yi <= 104
不会有两块石头放在同一个坐标点上
*/

import java.util.HashSet;

public class LeedCode974 {

    public static int removeStones(int[][] stones) {
        int len = stones.length;
        UnionFind unionFind = new UnionFind(20002);// 题目给的数值在0 -10000，0-10000给横坐标，10001-20002留给纵坐标
        HashSet<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            unionFind.union(stone[0], stone[1] + 10001);
        }
        for (int[] stone : stones) {
            set.add(unionFind.find(stone[0]));// 统计一下parent的数量，因为stones[i][0]和stones[i][1]为同一parent，搜索一个即可
        }
        return len - set.size();
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        int[][] stones = {{1, 2}, {1, 3}, {3, 3}, {3, 1}, {2, 1}, {1, 0}};
        int i = removeStones(stones);
        System.out.println(i);
    }
}
