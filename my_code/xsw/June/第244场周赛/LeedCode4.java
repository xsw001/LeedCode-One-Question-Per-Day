package xsw.June.第244场周赛;
/*
5779. 装包裹的最小浪费空间
给你 n 个包裹，你需要把它们装在箱子里，每个箱子装一个包裹。总共有 m 个供应商提供 不同尺寸 的箱子（每个规格都有无数个箱子）。如果一个包裹的尺寸 小于等于 一个箱子的尺寸，那么这个包裹就可以放入这个箱子之中。

包裹的尺寸用一个整数数组 packages 表示，其中 packages[i] 是第 i 个包裹的尺寸。供应商用二维数组 boxes 表示，其中 boxes[j] 是第 j 个供应商提供的所有箱子尺寸的数组。

你想要选择 一个供应商 并只使用该供应商提供的箱子，使得 总浪费空间最小 。对于每个装了包裹的箱子，我们定义 浪费的 空间等于 箱子的尺寸 - 包裹的尺寸 。总浪费空间 为 所有 箱子中浪费空间的总和。

比方说，如果你想要用尺寸数组为 [4,8] 的箱子装下尺寸为 [2,3,5] 的包裹，你可以将尺寸为 2 和 3 的两个包裹装入两个尺寸为 4 的箱子中，同时把尺寸为 5 的包裹装入尺寸为 8 的箱子中。总浪费空间为 (4-2) + (4-3) + (8-5) = 6 。
请你选择 最优 箱子供应商，使得 总浪费空间最小 。如果 无法 将所有包裹放入箱子中，请你返回 -1 。由于答案可能会 很大 ，请返回它对 109 + 7 取余 的结果。



示例 1：

输入：packages = [2,3,5], boxes = [[4,8],[2,8]]
输出：6
解释：选择第一个供应商最优，用两个尺寸为 4 的箱子和一个尺寸为 8 的箱子。
总浪费空间为 (4-2) + (4-3) + (8-5) = 6 。
示例 2：

输入：packages = [2,3,5], boxes = [[1,4],[2,3],[3,4]]
输出：-1
解释：没有箱子能装下尺寸为 5 的包裹。
示例 3：

输入：packages = [3,5,8,10,11,12], boxes = [[12],[11,9],[10,5,14]]
输出：9
解释：选择第三个供应商最优，用两个尺寸为 5 的箱子，两个尺寸为 10 的箱子和两个尺寸为 14 的箱子。
总浪费空间为 (5-3) + (5-5) + (10-8) + (10-10) + (14-11) + (14-12) = 9 。


提示：

n == packages.length
m == boxes.length
1 <= n <= 105
1 <= m <= 105
1 <= packages[i] <= 105
1 <= boxes[j].length <= 105
1 <= boxes[j][k] <= 105
sum(boxes[j].length) <= 105
boxes[j] 中的元素 互不相同 。
通过次数1,065提交次数4,249
 */

import java.util.*;

public class LeedCode4 {

    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        Arrays.sort(packages);
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + packages[i];
        }

        long res = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long t = 0;
            int low = 0;
            for (int b : box) {
                int idx = searchRight(packages, b, low);
                // 这里需要手动转 long
                t += ((idx - low) * (long) b - (preSum[idx] - preSum[low]));
                low = idx;
            }
            res = Math.min(res, t);
        }
        return res == Long.MAX_VALUE ? -1 : (int) (res % 1000000007);
    }

    private int searchRight(int[] packages, int target, int low) {
        int high = packages.length;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (packages[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}