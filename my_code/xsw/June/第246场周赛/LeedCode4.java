package xsw.June.第246场周赛;
/*

 */

import java.util.ArrayList;
import java.util.Arrays;

public class LeedCode4 {

    // 超出内存限制
    public static  int[] minDifference1(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] v = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            v[i][i] = -1;
            if (nums[i] == nums[i + 1])
                v[i][i + 1] = -1;
            else
                v[i][i + 1] = Math.abs(nums[i + 1] - nums[i]);
        }
        v[n-1][n-1] = -1;
        for (int i = n-1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int val = Math.abs(nums[i] - nums[j]);
                if(val == 0){
                    v[i][j] = Math.min(v[i][j - 1], v[i + 1][j]);
                    continue;
                }
                if (v[i + 1][j] == -1 && v[i][j - 1] == -1)
                    v[i][j] = val;
                else if (v[i + 1][j] == -1) {
                    v[i][j] = Math.min(v[i][j - 1], val);
                } else if (v[i][j - 1] == -1) {
                    v[i][j] = Math.min(v[i + 1][j], val);
                } else {
                    v[i][j] = Math.min(v[i][j - 1], Math.min(v[i + 1][j], val));
                }
            }
        }
        System.out.println(Arrays.deepToString(v));
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = v[queries[i][0]][queries[i][1]];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {4,5,2,2,7,10};
        int[][] aaa = {{2,3},{0,2},{1,2},{3,4},{3,4}};
        System.out.println(Arrays.toString(minDifference(data, aaa)));

    }

    //前缀和数组 pre[i][c] 表示数组 nums 的前缀 a[0..i−1] 中包含元素 c 的个数
    public static int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] pre = new int[n + 1][101];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 101; j++)
                pre[i + 1][j] = pre[i][j]; // 将前面的前缀信息也记录起来
            pre[i + 1][nums[i]]++;
        }
        int size = queries.length;
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            int left = queries[i][0], right = queries[i][1];
            int minValue = Integer.MAX_VALUE, last = -1;
            for (int j = 1; j <= 100; j++) {
                if (pre[left][j] < pre[right + 1][j]) { // 证明存在
                    if (last != -1) // last == -1 说明是这个范围内的第一个数，或者是重复的数，反正只有一个
                        minValue = Math.min(minValue, j - last);
                    last = j;
                }
            }
            if (minValue == Integer.MAX_VALUE) minValue = -1;
            ans[i] = minValue;
        }
        return ans;
    }

}