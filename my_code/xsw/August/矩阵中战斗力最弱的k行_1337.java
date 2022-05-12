package xsw.August;
/*
1337. 矩阵中战斗力最弱的 K 行
给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。

请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。

如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。

军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。



示例 1：

输入：mat =
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]],
k = 3
输出：[2,0,3]
解释：
每行中的军人数目：
行 0 -> 2
行 1 -> 4
行 2 -> 1
行 3 -> 2
行 4 -> 5
从最弱到最强对这些行排序后得到 [2,0,3,1,4]
示例 2：

输入：mat =
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]],
k = 2
输出：[0,2]
解释：
每行中的军人数目：
行 0 -> 1
行 1 -> 4
行 2 -> 1
行 3 -> 1
从最弱到最强对这些行排序后得到 [0,2,3,1]


提示：

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] 不是 0 就是 1
通过次数16,641提交次数24,587
 */

import java.util.*;

public class 矩阵中战斗力最弱的k行_1337 {

    // 3ms
    public static int[] kWeakestRows(int[][] mat, int k) {
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        for (int i = 0; i < mat.length; i++) {
            int num = numberOfOne(mat[i]);
            map.computeIfAbsent(num, v -> new ArrayList<Integer>()).add(i);
        }
        int[] ans = new int[k];
        int index = 0;
        while (index < k) {
            ArrayList<Integer> list = map.pollFirstEntry().getValue();
            for (Integer i : list) {
                ans[index++] = i;
                if (index == k)
                    break;
            }
        }
        return ans;
    }

    private static int numberOfOne(int[] arr) {
        int ans = 0;
        for (int i : arr) {
            if (i == 1)
                ++ans;
            else
                break;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {0, 0, 0, 0, 0, 0};
        System.out.println(numberOfOne1(data));

        int[][] mat = {{1, 0}, {0, 0}, {1, 0}};
        System.out.println(Arrays.toString(kWeakestRows(mat, 2)));
        System.out.println(Arrays.toString(kWeakestRows1(mat, 2)));
    }

    // 1ms
    public static int[] kWeakestRows1(int[][] mat, int k) {
        int l = mat.length;
        int[][] arr = new int[l][2];

        for (int i = 0; i < l; i++) {
            int num = numberOfOne1(mat[i]);
            if (mat[i][num] == 1)
                ++num;
            arr[i][0] = num;
            arr[i][1] = i;
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][1];
        }
        return ans;
    }

    private static int numberOfOne1(int[] ints) {
        int l = 0, r = ints.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (ints[mid] == 1)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }
}