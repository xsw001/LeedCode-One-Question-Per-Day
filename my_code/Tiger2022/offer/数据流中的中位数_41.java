package Tiger2022.offer;
/*
剑指 Offer 41. 数据流中的中位数
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例 1：

输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
示例 2：

输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]


限制：

最多会对 addNum、findMedian 进行 50000 次调用。
注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/

通过次数85,346提交次数146,166
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 数据流中的中位数_41 {

    @Test
    public void test() throws Exception {
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        System.out.println(m.findMedian());
        m.addNum(2);
        System.out.println(m.findMedian());
        m.addNum(3);
        System.out.println(m.findMedian());
        m.addNum(-4);
        System.out.println(m.findMedian());
        m.addNum(-5);
        System.out.println(m.findMedian());

    }

    class MedianFinder {
        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            min = new PriorityQueue<>(Comparator.comparingInt(o -> o));// 存较大的一半
            max = new PriorityQueue<>((o1, o2) -> o2 - o1);// 存较小的一半
        }

        public void addNum(int num) {
            if (max.isEmpty() || num < max.peek()) {
                max.add(num);
                if (max.size() > min.size() + 1)
                    min.add(max.poll());
            } else {
                min.add(num);
                if (min.size() > max.size())
                    max.add(min.poll());
            }
        }

        public double findMedian() {
            if (min.size() == max.size())
                return (max.peek() + min.peek()) / 2.0;
            return max.peek();
        }
    }
}
