package xsw.Nov_Dec;

/*
435. 无重叠区间
给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

注意:

可以认为区间的终点总是大于它的起点。
区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
示例 1:

输入: [ [1,2], [2,3], [3,4], [1,3] ]

输出: 1

解释: 移除 [1,3] 后，剩下的区间没有重叠。
示例 2:

输入: [ [1,2], [1,2], [1,2] ]

输出: 2

解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
示例 3:

输入: [ [1,2], [2,3] ]

输出: 0

解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class LeedCode435 {

    //尾部排序
    public static int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        int res = 1;
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int last = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= last){
                ++res;
                last = intervals[i][1];
            }
        }
        return intervals.length-res;
    }
    //头部排序
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //先排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //记录区间尾部的位置
        int end = intervals[0][1];
        //需要移除的数量
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                //如果重叠了，必须要移除一个，所以count要加1，
                //然后更新尾部的位置，我们取尾部比较小的
                end = Math.min(end, intervals[i][1]);
                count++;
            } else {
                //如果没有重叠，就不需要移除，只需要更新尾部的位置即可
                end = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[][] intervals = {{1, 3}, {2, 3}, {3, 4}, {1, 2}};
        int i = eraseOverlapIntervals(intervals);
        int i1 = eraseOverlapIntervals1(intervals);
        System.out.println(i);
        System.out.println(i1);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
}
