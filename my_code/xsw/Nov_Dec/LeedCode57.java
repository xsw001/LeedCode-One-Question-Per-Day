package xsw.Nov_Dec;/*
57. 插入区间
给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

示例 1：

输入：intervals = [[1,3],[6,9]], 2,5
输出：[[1,5],[6,9]]
示例 2：

输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeedCode57 {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        int[][] res = new int[len+1][2];
        int i = 0;
        int j = 0;
        //考虑：
        //[] intervals为空
        //[5,7]
        if (len == 0) {//直接接在末尾
            res[j]=newInterval;
            return res;
        }
        //把小于待插入区间起始坐标的所有区间放到新区间列表
        for (; i < len; i++) {
            if (intervals[i][1] < newInterval[0]) {
                res[j++] = intervals[i];
            } else
                break;
        }
        //考虑：
        //[[1,2]] intervals最大的数小于newInterval最小的数
        //[5,7]
        if (i == len){//直接接在末尾
            res[j]=newInterval;
            return res;
        }
        //寻找新数组的改变的新起点
        int start = Math.min(intervals[i][0], newInterval[0]);
        //寻找新数组的改变的新终点
        int end = 0;
        //第一种情况
        while (i<len && newInterval[1] > intervals[i][1]) {
            ++i;
        }
        //第二种情况
        if (i<len && newInterval[1] >= intervals[i][0]) {
            end = intervals[i][1];
            ++i;
        } else//第三种情况
            end = newInterval[1];
        //改变插入节点所影响的区间
        res[j][0] = start;
        res[j][1] = end;
        ++j;
        //把大于待插入区间结束坐标的所有区间放到新区间列表
        for (; i < len; i++)
            res[j++] = intervals[i];

        return Arrays.copyOfRange(res, 0, j);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,6}};
        int[] newInterval = {7, 8};
        int[][] insert = insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(insert));
    }

    /*
    找出所有与区间 S 重叠的区间集合 X′；
    将X′中的所有区间连带上区间 S 合并成一个大区间；
    最终的答案即为不与 X′重叠的区间以及合并后的大区间。
    */
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
