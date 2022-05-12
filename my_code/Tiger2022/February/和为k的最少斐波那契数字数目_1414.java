package Tiger2022.February;
/*
1414. 和为 K 的最少斐波那契数字数目
给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。

斐波那契数字定义为：

F1 = 1
F2 = 1
Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
数据保证对于给定的 k ，一定能找到可行解。



示例 1：

输入：k = 7
输出：2
解释：斐波那契数字为：1，1，2，3，5，8，13，……
对于 k = 7 ，我们可以得到 2 + 5 = 7 。
示例 2：

输入：k = 10
输出：2
解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
示例 3：

输入：k = 19
输出：3
解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。


提示：

1 <= k <= 10^9
通过次数11,099提交次数17,176
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class 和为k的最少斐波那契数字数目_1414 {

    public int findMinFibonacciNumbers(int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int a = 1, b = 1;
        list.add(1);
        list.add(1);
        while (b <= k) {
            if (b == k)
                return 1;
            int t = a + b;
            list.add(t);
            a = b;
            b = t;
        }
        int l = list.size();
        int ans = l;
        // 多余了，贪心策略，一编就行
        for (int i = l - 1; i >= 0; i--) {
            int rest = k;
            int count = 0;
            for (int j = i - 1; j >= 0; j--) {
                int num = list.get(j);
                if (rest == 0)
                    break;
                if (rest >= num) {
                    rest -= num;
                    ++count;
                }
            }
            if (rest == 0)
                ans = Math.max(ans, count);
        }
        return ans;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
    }

    class Solution {
        public int findMinFibonacciNumbers(int k) {
            ArrayList<Integer> list = new ArrayList<>();
            int a = 1, b = 1;
            list.add(1);
            list.add(1);
            while (b <= k) {
                if (b == k)
                    return 1;
                int t = a + b;
                list.add(t);
                a = b;
                b = t;
            }
            int l = list.size();
            int ans = 0;
            for (int i = l - 1; i >= 0 && k > 0; i--) {
                int num = list.get(i);
                if (k >= num) {
                    k -= num;
                    ++ans;
                }
            }
            return ans;
        }
    }
}