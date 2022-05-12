package xsw.July;
/*
给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：

arr 中 第一个 元素必须为 1 。
任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
你可以执行以下 2 种操作任意次：

减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
重新排列 arr 中的元素，你可以以任意顺序重新排列。
请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。

 

示例 1：

输入：arr = [2,2,1,2,1]
输出：2
解释：
我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
arr 中最大元素为 2 。
示例 2：

输入：arr = [100,1,1000]
输出：3
解释：
一个可行的方案如下：
1. 重新排列 arr 得到 [1,100,1000] 。
2. 将第二个元素减小为 2 。
3. 将第三个元素减小为 3 。
现在 arr = [1,2,3] ，满足所有条件。
arr 中最大元素为 3 。
示例 3：

输入：arr = [1,2,3,4,5]
输出：5
解释：数组已经满足所有条件，最大元素为 5 。
 

提示：

1 <= arr.length <= 105
1 <= arr[i] <= 109
通过次数7,514提交次数12,089

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 减小和重新排列数组后的最大元素_1846 {

    // 感觉......被秀到了
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int l = arr.length;
        Arrays.sort(arr);
        if(arr[0] != 1)
            arr[0] = 1;
        for (int i = 1; i < l; i++) {
            if(arr[i] == arr[i-1])
                continue;
            arr[i] = arr[i-1]+1;
        }

        return  arr[l-1];
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 3, 5, 5, 5, 5, 1, 1, 10};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(maximumElementAfterDecrementingAndRearranging3(data));
    }

    // 思路错误
    public static int maximumElementAfterDecrementingAndRearranging1(int[] arr) {
        int l = arr.length;
        boolean[] count = new boolean[l+1];
        for (int i : arr) {
            if(i <= l)
                count[i] = true;
        }
        int ans = 1;
        for (int j : arr) {
            if(j > l) {
                while (count[ans])
                    ++ans;
                count[ans] = true;
            }
        }

        return  ans;
    }

    // 不容易想
    public static int maximumElementAfterDecrementingAndRearranging3(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] == 0) {
                ++miss;
            } else {
                // 可以将多余的 cnt[i]−1 个元素减小，补充到之前缺失的元素上
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        return n - miss;
    }
}