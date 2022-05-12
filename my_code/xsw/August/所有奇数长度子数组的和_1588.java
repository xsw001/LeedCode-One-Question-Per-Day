package xsw.August;
/*
1588. 所有奇数长度子数组的和
给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。

子数组 定义为原数组中的一个连续子序列。

请你返回 arr 中 所有奇数长度子数组的和 。



示例 1：

输入：arr = [1,4,2,5,3]
输出：58
解释：所有奇数长度子数组和它们的和为：
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
示例 2：

输入：arr = [1,2]
输出：3
解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
示例 3：

输入：arr = [10,11,12]
输出：66


提示：

1 <= arr.length <= 100
1 <= arr[i] <= 1000
通过次数23,033提交次数28,413
 */

import java.util.ArrayList;

public class 所有奇数长度子数组的和_1588 {

    public static int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + arr[i];
        }
        for (int i = 1; i <= n; i += 2) {
            if (i == 1 || i == n)
                ans += pre[n];
            else {
                for (int j = 0; j + i <= n; j++) {
                    ans += pre[j + i] - pre[j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 1};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(sumOddLengthSubarrays(data));
    }

    /*
    遍历一遍所有的元素，然后查看这个元素会在多少个长度为奇数的数组中出现过。

比如题目给出的第一个测试用例 [1, 4, 2, 5, 3] 中；

1 在 3 个长度为奇数的数组中出现过：[1], [1, 4, 2], [1, 4, 2, 5, 3]；所以最终的和，要加上 1 * 3；

4 在 4 个长度为奇数的数组中出现过：[4], [4, 2, 5], [1, 4, 2], [1, 4, 2, 5, 3]；所以最终和，要加上 4 * 4；

2 在 5 个长度为奇数的数组中出现过：[2], [2, 5, 3], [4, 2, 5], [1, 4, 2], [1, 4, 2, 5, 3]；所以最终和，要加上 5 * 2；

...

下面的关键就是，如何计算一个数字在多少个奇数长度的数组中出现过？

对于一个数字，它所在的数组，可以在它前面再选择 0, 1, 2, ... 个数字，一共有 left = i + 1 个选择；

可以在它后面再选择 0, 1, 2, ... 个数字，一共有 right = n - i 个选择。

如果在前面选择了偶数个数字，那么在后面，也必须选择偶数个数字，这样加上它自身，才构成奇数长度的数组；

如果在前面选择了奇数个数字，那么在后面，也必须选择奇数个数字，这样加上它自身，才构成奇数长度的数组；

数字前面共有 left 个选择，其中偶数个数字的选择方案有 left_even = (left + 1) / 2 个，奇数个数字的选择方案有 left_odd = left / 2 个；

数字后面共有 right 个选择，其中偶数个数字的选择方案有 right_even = (right + 1) / 2 个，奇数个数字的选择方案有 right_odd = right / 2 个；

所以，每个数字一共在 left_even * right_even + left_odd * right_odd 个奇数长度的数组中出现过。
     */
    public int sumOddLengthSubarray(int[] arr) {
        int len = arr.length;

        int res = 0;
        int left = 0, right = len - 1;
        int leftOdd = 0, rightOdd = 0;
        int leftEven = 0, rightEven = 0;

        for (int i = 0; i < len; i++) {
            left = i + 1;
            right = len - i;
            leftOdd = left / 2;
            rightOdd = right / 2;

            leftEven = (left + 1) / 2;
            rightEven = (right + 1) / 2;

            res += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }


        return res;
    }
}