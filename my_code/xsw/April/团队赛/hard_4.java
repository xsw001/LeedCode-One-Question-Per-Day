package xsw.April.团队赛;
/*
4. 最多牌组数
通过的用户数0
尝试过的用户数7
用户总通过次数0
用户总提交次数10
题目难度Hard
麻将的游戏规则中，共有两种方式凑成「一组牌」：

顺子：三张牌面数字连续的麻将，例如 [4,5,6]
刻子：三张牌面数字相同的麻将，例如 [10,10,10]
给定若干数字作为麻将牌的数值（记作一维数组 tiles），请返回所给 tiles 最多可组成的牌组数。

注意：凑成牌组时，每张牌仅能使用一次。

示例 1：

输入：tiles = [2,2,2,3,4]

输出：1

解释：最多可以组合出 [2,2,2] 或者 [2,3,4] 其中一组牌。

示例 2：

输入：tiles = [2,2,2,3,4,1,3]

输出：2

解释：最多可以组合出 [1,2,3] 与 [2,3,4] 两组牌。

提示：

1 <= tiles.length <= 10^5
1 <= tiles[i] <= 10^9 */
import java.util.*;
public class hard_4 {

    public static int maxGroupNumber(int[] tiles) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int key : tiles) {
            if (!map.containsKey(key)) {
                map.put(key, 0);
            }

            map.put(key, map.get(key) + 1);
        }

        ArrayList<Node> list = new ArrayList<Node>();
        for (Integer key : map.keySet()) {
            int value = map.get(key);

            Node node = new Node(key, value);
            list.add(node);
        }

        list.sort(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.value - b.value;
            }
        });

        int n = list.size();

        if (n == 1) {
            return list.get(0).count / 3;
        } else if (n == 2) {
            return list.get(0).count / 3 + list.get(1).count / 3;
        }

        boolean[] isconnect = new boolean[n];
        for (int i = 2; i < n; i++) {
            if ((list.get(i).value == (list.get(i - 1).value + 1))
                    && (list.get(i).value == (list.get(i - 2).value + 2))) {
                isconnect[i] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            // System.out.printf("%d %d %s\n", list.get(i).value,
            // list.get(i).count, isconnect[i]);
        }

        int ans = 0;
        for (Node node : list) {
            int count = node.count;
            if (count > 8) {
                if (count % 3 == 0) {
                    ans = ans + (count - 6) / 3;
                    node.count = 6;
                } else if (count % 3 == 1) {
                    ans = ans + (count - 7) / 3;
                    node.count = 7;
                } else {
                    ans = ans + (count - 8) / 3;
                    node.count = 8;
                }
            }
        }

        int[][][] dp = new int[n][9][9];
        int[][][] max = new int[n][9][9];

        int inc = 0;
        for (int tail = 0; tail <= list.get(1).count; tail++) {
            for (int head = 0; head <= list.get(0).count; head++) {
                dp[1][tail][head] = tail / 3 + head / 3;

                if (head > 0) {
                    max[1][tail][head] = Math.max(max[1][tail][head - 1], dp[1][tail][head]);
                } else {
                    max[1][tail][head] = dp[1][tail][head];
                }

                inc = Math.max(inc, dp[1][tail][head]);
            }
        }

        for (int i = 2; i < n; i++) {
            int n1 = list.get(i).count;
            int n2 = list.get(i - 1).count;
            int n3 = list.get(i - 2).count;

            for (int tail = 0; tail <= n1; tail++) {
                for (int head = 0; head <= n2; head++) {
                    int nowmax = max[i - 1][head][n3] + tail / 3;
                    if (isconnect[i]) {
                        if (tail > 0 && head > 0 && n3 > 0) {
                            nowmax = Math.max(nowmax, max[i - 1][head - 1][n3 - 1] + 1 + (tail - 1) / 3);
                        }
                        if (tail > 1 && head > 1 && n3 > 1) {
                            nowmax = Math.max(nowmax, max[i - 1][head - 2][n3 - 2] + 2 + (tail - 2) / 3);
                        }
                    }
                    dp[i][tail][head] = nowmax;
                    if (head > 0) {
                        max[i][tail][head] = Math.max(max[i][tail][head - 1], dp[i][tail][head]);
                    } else {
                        max[i][tail][head] = dp[i][tail][head];
                    }

                    // System.out.printf("%d %d %d %d \n", i, tail, head,
                    // dp[i][tail][head]);

                    inc = Math.max(inc, dp[i][tail][head]);
                }
            }
        }

        return ans + inc;
    }

    static class Node {
        int value;
        int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }

    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};

    }

}