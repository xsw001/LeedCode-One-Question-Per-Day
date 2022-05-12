package xsw.December21;
/*
846. 一手顺子
Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。

给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。



示例 1：

输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
输出：true
解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
示例 2：

输入：hand = [1,2,3,4,5], groupSize = 4
输出：false
解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。


提示：

1 <= hand.length <= 104
0 <= hand[i] <= 109
1 <= groupSize <= hand.length


注意：此题目与 1296 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/

通过次数12,572提交次数23,307
 */

import org.junit.Test;

import java.util.*;

public class 一手顺子_846 {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0 || n < groupSize)
            return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        while ((!map.isEmpty())) {
            int start = map.firstKey();
            for (int i = 0; i < groupSize; i++) {
                int k = start + i;
                if (!map.containsKey(k))
                    return false;
                Integer v = map.get(k);
                if (v == 1)
                    map.remove(k);
                else
                    map.put(k, v - 1);
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] data = {1,2,3,6,2,3,4,7,8,1,2,3,6,2,3,4,7,8,1,2,3,6,2,3,4,7,8};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(isNStraightHand(data, 3));
    }

    /*// 思路错误
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0 || n < groupSize)
            return false;
        Arrays.sort(hand);
        LinkedList<Integer> list = new LinkedList<>();
        int[] arr = new int[groupSize];
        int start = 0;
        int i = 0;
        while (i < hand.length){
            if (start == 0)
                if (list.isEmpty())
                    arr[start++] = hand[i++];
                else
                    arr[start++] = list.pollFirst();
            else {
                if (hand[i] == arr[start - 1])
                    list.addLast(hand[i++]);
                else if (hand[i] == arr[start - 1] + 1)
                    arr[start++] = hand[i++];
                else
                    return false;
            }
            if(start == groupSize)
                start = 0;
        }
        return true;
    }*/
    class Solution {
        // 和我的思路一样，实现不一样
        public boolean isNStraightHand(int[] hand, int groupSize) {
            int n = hand.length;
            if (n % groupSize != 0) {
                return false;
            }
            Arrays.sort(hand);
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int x : hand) {
                cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            }
            for (int x : hand) {
                if (!cnt.containsKey(x)) {
                    continue;
                }
                for (int j = 0; j < groupSize; j++) {
                    int num = x + j;
                    if (!cnt.containsKey(num)) {
                        return false;
                    }
                    cnt.put(num, cnt.get(num) - 1);
                    if (cnt.get(num) == 0) {
                        cnt.remove(num);
                    }
                }
            }
            return true;
        }
    }
}