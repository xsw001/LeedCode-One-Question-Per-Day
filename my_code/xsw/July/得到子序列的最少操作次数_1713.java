package xsw.July;
/*
1713. 得到子序列的最少操作次数
给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。

每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。

请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。

一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。



示例 1：

输入：target = [5,1,3], arr = [9,4,2,3,4]
输出：2
解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
示例 2：

输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
输出：3


提示：

1 <= target.length, arr.length <= 105
1 <= target[i], arr[i] <= 109
target 不包含任何重复元素。
通过次数4,190提交次数8,525
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 得到子序列的最少操作次数_1713 {

    // 转换成第300题，求最长递增子序列的长度
    public static int minOperations(int[] target, int[] arr) {
        // 由于 target 的元素互不相同，我们可以用一个哈希表记录 target 的每个元素所处的下标
        // 并将 arr 中的元素映射到下标上
        // 对于不存在于 target 中的元素，由于其必然不会在最长公共子序列中，可将其忽略。
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            if (map.containsKey(i))
                list.add(map.get(i));
        }
        return target.length - lengthOfLIS(list);
    }

    // 第300题，求最长递增子序列的长度
    public static int lengthOfLIS(ArrayList<Integer> nums) {
        int len = 1, n = nums.size();// 设当前已求出的最长上升子序列的长度为 len（初始时为 1）
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];// d[i] 是关于 i 单调递增的
        d[0] = 100;// 无所谓的值，可以不用管
        d[len] = nums.get(0);
        for (int i = 1; i < n; ++i) {
            if (nums.get(i) > d[len]) {// 如果 nums[i]>d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；
                d[++len] = nums.get(i);
            } else {// 否则，在 d 数组中二分查找，找到第一个比 nums[i] 小的数 d[k]，并更新 d[k+1]=nums[i]。
                int l = 0, r = len;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    if (d[mid] < nums.get(i)) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                // 边界问题。
                // 如果没找到，l 为 0，应该更新 1。
                // 如果没找到，l 为小于目标值的最小值，是存在于d中的，要把目标值放在他的后边（l+1）
                // 所以找不找得到都更新 l + 1 位置
                d[l + 1] = nums.get(i);
            }
        }
        return len;
    }
    public static int lengthOfLIS1(ArrayList<Integer> nums) {
        int len = 1, n = nums.size();// 设当前已求出的最长上升子序列的长度为 len（初始时为 1）
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];// d[i] 是关于 i 单调递增的
        d[0] = -1;// 小于1就行
        d[len] = nums.get(0);
        for (int i = 1; i < n; ++i) {
            if (nums.get(i) > d[len]) {// 如果 nums[i]>d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；
                d[++len] = nums.get(i);
            } else {// 否则，在 d 数组中二分查找，找到第一个大于等于 nums[i] 的数 d[k]，并更新 d[k]=nums[i]。
                int l = 0, r = len;// l 可以从 0 或 1 开始，都行。0 较合理
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums.get(i)) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                // 边界问题
                d[l] = nums.get(i);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] data = {19,15,2,3,10,6,7,4,8,14};
        int[] dat = {9,7,9,2,15,14,3,8,14,8};
        System.out.println(minOperations(data, dat));
        Integer[] a = {6,2,1,9,3,8,9,8};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(a));
        System.out.println(lengthOfLIS(list));
        int[] b = {100, 2, 3, 5, 5, 6, 7};
        help(b, 1);
    }

    public static void help(int[] d, int num) {
        int l = 0, r = d.length;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (d[mid] < num) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(d[l]);
    }
}