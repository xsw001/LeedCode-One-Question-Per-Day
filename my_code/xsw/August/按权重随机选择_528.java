package xsw.August;
/*
528. 按权重随机选择
给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。

例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。

也就是说，选取下标 i 的概率为 w[i] / sum(w) 。



示例 1：

输入：
["Solution","pickIndex"]
[[[1]],[]]
输出：
[null,0]
解释：
Solution solution = new Solution([1]);
solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
示例 2：

输入：
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
输出：
[null,1,1,1,1,0]
解释：
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
诸若此类。


提示：

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex 将被调用不超过 10000 次
通过次数18,142提交次数37,493
请问您在哪类招聘中遇到此题？
 */

import java.util.*;

public class 按权重随机选择_528 {

    /*
    我们先考虑3台服务器的情况，假设他们的权重分别为：[3, 5, 2]，我们应该怎么来搞呢？

通常的做法，是生成一个10以内的随机数，如果这个随机数小于3，就选第一台机器；如果这个随机数小于8（3 + 5），就选第二台机器；否则，就选第三台。

这其实就是前缀和的思想，我们从第二台开始与随机数的比较是需要加上前面机器的权重的，这样更好计算。
     */
    /*
    设数组 w 的权重之和为 total。
    根据题目的要求，我们可以看成将 [1,total] 范围内的所有整数分成 n 个部分（其中 n 是数组 w 的长度），
    第 i 个部分恰好包含 w[i] 个整数，并且这 n 个部分两两的交集为空。
    随后我们在 [1,total] 范围内随机选择一个整数 x，
    如果整数 x 被包含在第 i 个部分内，我们就返回 i。
     */
    class Solution {
        int[] pre;
        int total;

        public Solution(int[] w) {
            pre = new int[w.length];
            pre[0] = w[0];
            for (int i = 1; i < w.length; ++i) {
                pre[i] = pre[i - 1] + w[i];
            }
            total = Arrays.stream(w).sum();
        }

        public int pickIndex() {
            int x = (int) (Math.random() * total) + 1;
            return binarySearch(x);
        }

        private int binarySearch(int x) {
            int low = 0, high = pre.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (pre[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/random-pick-with-weight/solution/an-quan-zhong-sui-ji-xuan-ze-by-leetcode-h13t/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}