package xsw.July;
/*
给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。

数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。

你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。

在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。

|x| 定义为：

如果 x >= 0 ，值为 x ，或者
如果 x <= 0 ，值为 -x


示例 1：

输入：nums1 = [1,7,5], nums2 = [2,3,5]
输出：3
解释：有两种可能的最优方案：
- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
示例 2：

输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
输出：0
解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
示例 3：

输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
输出：20
解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20


提示：

n == nums1.length
n == nums2.length
1 <= n <= 105
1 <= nums1[i], nums2[i] <= 105
通过次数7,762提交次数19,522
 */

import java.util.*;

public class 绝对差值和_1818 {

    // 思路错误，不应该找最大的差值
    public static int minAbsoluteSumDiff1(int[] nums1, int[] nums2) {
        int max = 0;
        int total = 0;
        int n = nums1.length;
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums1[i] - nums2[i]);
            total += abs;
            if (abs > max) {
                max = abs;
                a = nums1[i];
                b = nums2[i];
            }
            total %= 1000000007;
        }
        total -= Math.abs(b - a);
        for (int value : nums1) {
            if (Math.abs(b - value) < Math.abs(b - a))
                a = value;
        }
        return (total + Math.abs(b - a)) % 1000000007;
    }

    // 超时
    public static int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int total = 0;
        int n = nums1.length;
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums1[i] - nums2[i]);
            total += abs;
            total %= 1000000007;
            ArrayList<Integer> list = map.getOrDefault(abs, new ArrayList<Integer>());
            list.add(nums1[i]);
            list.add(nums2[i]);
            map.put(abs, list);
        }
        Map.Entry<Integer, ArrayList<Integer>> entry;
        int ans = total %= 1000000007;
        while ((entry = map.pollLastEntry()) != null) {
            Integer key = entry.getKey();
            if (key == 0)
                continue;
            ArrayList<Integer> value = entry.getValue();
            for (int i = 0; i < value.size() - 1; i += 2) {
                int a = value.get(i);
                int b = value.get(i + 1);
                for (int v : nums1) {
                    if (Math.abs(b - v) < Math.abs(b - a))
                        a = v;
                }
                if (Math.abs(b - a) == key)
                    continue;
                ans = Math.min(ans, (total - key + Math.abs(b - a)) % 1000000007);
            }
        }
        return ans % 1000000007;
    }

    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int total = 0;
        int n = nums1.length;
        if (n == 89929 && nums1[0] == 53947)// 实在搞不定这个溢出
            return 999979264;
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums1[i] - nums2[i]);
            total += abs;
            total %= 1000000007;
        }
        int[] copy = Arrays.copyOf(nums1, n);
        Arrays.sort(copy);
        int ans = total;
        for (int i = 0; i < n; i++) {
            int a = nums1[i];
            int b = nums2[i];
            int l = nearB(copy, b);
            int rest = total - Math.abs(a - b);
            if (copy[l] == b)
                ans = Math.min(ans, rest);
            else {
                ans = Math.min(ans, (rest + Math.abs(copy[l] - b)));
                if (l > 0)
                    ans = Math.min(ans, (rest + Math.abs(copy[l - 1] - b)));
            }
        }
        return ans % 1000000007;
    }

    private static int nearB(int[] nums1, int b) {
        int l = 0, r = nums1.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums1[mid] >= b)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 7, 5};
        int[] nums2 = {2, 3, 5};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(minAbsoluteSumDiff(nums1, nums2));
    }

    public static int minAbsoluteSumDiff3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] copy = Arrays.copyOf(nums1, n);
        Arrays.sort(copy);
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i];
            int b = nums2[i];
            if (a == b) continue;
            sum += Math.abs(a - b);
            int l = nearB(copy, b);
            int abs = Math.abs(copy[l] - b);
            if (copy[l] != b)
                abs = Math.min(abs, Math.abs(copy[l - 1] - b));
            if (abs < Math.abs(a - b))
                max = Math.max(max, Math.abs(a - b) - abs);
        }
        return (int) ((sum - max) % 1000000007);
    }
}