package xsw.July;
/*
1893. 检查是否区域内所有整数都被覆盖
给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。

如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。

已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。



示例 1：

输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
输出：true
解释：2 到 5 的每个整数都被覆盖了：
- 2 被第一个区间覆盖。
- 3 和 4 被第二个区间覆盖。
- 5 被第三个区间覆盖。
示例 2：

输入：ranges = [[1,10],[10,20]], left = 21, right = 21
输出：false
解释：21 没有被任何一个区间覆盖。


提示：

1 <= ranges.length <= 50
1 <= starti <= endi <= 50
1 <= left <= right <= 50
通过次数6,885提交次数11,455
 */

import java.util.ArrayList;

public class 检查是否区域内所有整数都被覆盖_1893 {

    public static boolean isCovered1(int[][] ranges, int left, int right) {
        for (int i = left; i <= right; i++) {
            boolean flag = false;
            for (int[] range : ranges) {
                if(i >= range[0] && i <= range[1]) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    /*
        朴素的做法是，遍历 ranges 中的所有区间 [l,r]，将区间内每个整数的 cnt 值加上 1。
        遍历结束后，检查 [left,right] 内的每个整数的 cnt 值是否均大于 0，
        是则返回 true，否则返回 false。
     */
    // 差分数组
    /*
        将原数组y看成全为 0 的
        然后ranges区间内的数组元素加1
        差分数组的前缀和即为改变后的数组sum
        在判断
     */
    public static boolean isCovered(int[][] ranges, int left, int right) {
        int[] d = new int[52]; // 1 <= ranges.length <= 50
        // 维护查分数组
        for (int[] range : ranges) {
            d[range[0]]++;
            d[range[1]+1]--;
        }
        // 计算差分数组的前缀和
        int[] sum = new int[52];
        for (int i = 1; i < d.length; i++) {
            sum[i] = sum[i-1] + d[i];
        }
        // 判断
        for (int i = left; i <= right; i++) {
            if(sum[i] <= 0)
                return false;
        }
        return true;
    }
}