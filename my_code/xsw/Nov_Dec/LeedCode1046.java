package xsw.Nov_Dec;

/*
1046. 最后一块石头的重量
有一堆石头，每块石头的重量都是正整数。

每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。



示例：

输入：[2,7,4,1,8,1]
输出：1
解释：
先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。


提示：

1 <= stones.length <= 30
1 <= stones[i] <= 1000
*/

import java.util.*;

public class LeedCode1046 {

    //超级慢
    public static int lastStoneWeight1(int[] stones) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int stone : stones) {
            list.addLast(stone);
        }
        Collections.sort(list);
        while (list.size() > 1) {
            int a = list.pollLast();
            int b = list.pollLast();
            if (a != b) {
                list.addFirst(a - b);
                Collections.sort(list);
            }
        }
        return list.isEmpty() ? 0 : list.poll();
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight2(new int[]{2, 7, 4, 1, 8, 1}));
    }

    public static int lastStoneWeight(int[] stones) {
        Arrays.sort(stones);
        int l = stones.length - 1;
        while (l > 0) {
            if (stones[l] == stones[l - 1])
                l -= 2;
            else {
                stones[l - 1] = stones[l] - stones[l - 1];
                --l;
                Arrays.sort(stones);
            }
        }
        return l < 0 ? 0 : stones[l];
    }

    //使用堆
    public static int lastStoneWeight2(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        for (int stone : stones) {
            pq.add(stone);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a != b) {
                pq.add(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
