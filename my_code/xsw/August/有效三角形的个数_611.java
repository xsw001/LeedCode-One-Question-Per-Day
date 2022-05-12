package xsw.August;
/*
611. 有效三角形的个数
给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

示例 1:

输入: [2,2,3,4]
输出: 3
解释:
有效的组合是:
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
注意:

数组长度不超过1000。
数组里整数的范围为 [0, 1000]。
通过次数29,760提交次数57,191
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 有效三角形的个数_611 {

    public static int triangleNumber(int[] nums) {
        int size = nums.length;
        if (size < 3)
            return 0;
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < size; i++) {
            int a = nums[i];
            for (int j = i + 1; j < size - 1; j++) {
                int b = nums[j];
                int max = b + a - 1;
                int l = j + 1, r = size - 1;
                while (l < r) {
                    int c = (l + r + 1) / 2;
                    if (nums[c] > max)
                        r = c - 1;
                    else
                        l = c;
                }
                if (l == j + 1 && !triangle(a, b, nums[l]))
                    continue;
                ans += (l - j);
            }
        }
        return ans;
    }

    private static boolean triangle(int a, int b, int c) {
        return a + b > c && Math.abs(b - a) < c;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        int[] dataq = {1, 2, 3, 4, 5, 6, 7, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        System.out.println(triangleNumber(data));
        System.out.println(Solution.triangleNumber(dataq));
    }


    static class Solution {
        public static int triangleNumber(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                int k = i;
                for (int j = i + 1; j < n; ++j) {
                    while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                        ++k; // k 没有回退，递增的。所以，是 o(n^2) 的双指针算法
                    }
                    ans += Math.max(k - j, 0);
                }
            }
            return ans;
        }
    }

/*    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/valid-triangle-number/solution/you-xiao-san-jiao-xing-de-ge-shu-by-leet-t2td/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}