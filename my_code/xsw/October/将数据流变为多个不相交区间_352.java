package xsw.October;
/*
352. 将数据流变为多个不相交区间
 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。

实现 SummaryRanges 类：

SummaryRanges() 使用一个空数据流初始化对象。
void addNum(int val) 向数据流中加入整数 val 。
int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。


示例：

输入：
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
输出：
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

解释：
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]


提示：

0 <= val <= 104
最多调用 addNum 和 getIntervals 方法 3 * 104 次


进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 */

import xsw.Nov_Dec.UnionFind;

import java.util.*;

public class 将数据流变为多个不相交区间_352 {

    class SummaryRanges1 {

        PriorityQueue<Integer> queue;
        HashSet<Integer> set;

        public SummaryRanges1() {
            queue = new PriorityQueue<>();
            set = new HashSet<>();
        }

        public void addNum(int val) {
            if (set.add(val))
                queue.add(val);
        }

        public int[][] getIntervals() {
            ArrayList<int[]> list = new ArrayList<>();
            PriorityQueue<Integer> nums = new PriorityQueue<>(this.queue);
            while (!nums.isEmpty()) {
                int start = nums.poll();
                int end = start;
                while (!nums.isEmpty() && nums.peek() == end + 1)
                    end = nums.poll();
                int[] range = {start, end};
                list.add(range);
            }
            return list.toArray(new int[list.size()][]);
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    class SummaryRanges {
        TreeMap<Integer, Integer> intervals;

        public SummaryRanges() {
            intervals = new TreeMap<Integer, Integer>();
        }

        public void addNum(int val) {
            // 找到 l1 最小的且满足 l1 > val 的区间 interval1 = [l1, r1]
            // 如果不存在这样的区间，interval1 为尾迭代器
            Map.Entry<Integer, Integer> interval1 = intervals.ceilingEntry(val + 1);
            // 找到 l0 最大的且满足 l0 <= val 的区间 interval0 = [l0, r0]
            // 在有序集合中，interval0 就是 interval1 的前一个区间
            // 如果不存在这样的区间，interval0 为尾迭代器
            Map.Entry<Integer, Integer> interval0 = intervals.floorEntry(val);

            if (interval0 != null && interval0.getKey() <= val && val <= interval0.getValue()) {
                // 情况一
                return;
            } else {
                boolean leftAside = interval0 != null && interval0.getValue() + 1 == val;
                boolean rightAside = interval1 != null && interval1.getKey() - 1 == val;
                if (leftAside && rightAside) {
                    // 情况四
                    int left = interval0.getKey(), right = interval1.getValue();
                    intervals.remove(interval0.getKey());
                    intervals.remove(interval1.getKey());
                    intervals.put(left, right);
                } else if (leftAside) {
                    // 情况二
                    intervals.put(interval0.getKey(), interval0.getValue() + 1);
                } else if (rightAside) {
                    // 情况三
                    int right = interval1.getValue();
                    intervals.remove(interval1.getKey());
                    intervals.put(val, right);
                } else {
                    // 情况五
                    intervals.put(val, val);
                }
            }
        }

        public int[][] getIntervals() {
            int size = intervals.size();
            int[][] ans = new int[size][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
                int left = entry.getKey(), right = entry.getValue();
                ans[index][0] = left;
                ans[index][1] = right;
                ++index;
            }
            return ans;
        }
    }

    class SummaryRanges2 {

        // 存储右边界
        private int[] father = new int[10001];
        // 使用有序的set存储左边界
        private Set<Integer> set = new TreeSet<>();

        public SummaryRanges2() {
            // 元素值的范围为 0~10000，所以，初始化为-1
            Arrays.fill(father, -1);
        }

        public void addNum(int val) {
            if (father[val] == -1) {
                father[val] = val;
                // 将当前元素加入set中
                set.add(val);
                // 先合并右边，再合并左边
                union(val, val + 1);
                union(val - 1, val);
            }
        }

        private int find(int x) {
            if (father[x] != x)
                return father[x] = find(father[x]);
            return father[x];
        }

        private void union(int x, int y) {
            if (x >= 0 && x <= 10000 && father[x] != -1 && father[y] != -1) {
                int fx = find(x);
                int fy = find(y);
                if (fx != fy) {
                    father[fx] = fy;
                    // 可以合并，set中删除右边的那个数
                    set.remove(y);
                }
            }
        }

        public int[][] getIntervals() {
            List<int[]> list = new ArrayList<>();

            // 遍历set拿到左边界
            for (int start : set) {
                int end = find(start);
                list.add(new int[]{start, end});
            }

            return list.toArray(new int[list.size()][2]);
        }
    }
/*
    作者：tong-zhu
    链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/solution/tong-ge-lai-shua-ti-la-yi-ti-san-jie-mo-tpqtc/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}