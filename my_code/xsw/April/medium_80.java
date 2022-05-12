package xsw.April;
/*
80. 删除有序数组中的重复项 II
给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。



说明：

为什么返回数值是整数，但输出的答案是数组呢？

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}


示例 1：

输入：nums = [1,1,1,2,2,3]
输出：5, nums = [1,1,2,2,3]
解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
示例 2：

输入：nums = [0,0,1,1,1,1,2,3,3]
输出：7, nums = [0,0,1,1,2,3,3]
解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。


提示：

0 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums 已按升序排列
通过次数89,055提交次数152,116
 */

import java.util.Arrays;

public class medium_80 {

    public static int removeDuplicates1(int[] nums) {
        int l = nums.length;
        int res = 0;
        for (int i = 1; i < l; ) {
            if (nums[i] == nums[i - 1]) {
                int j = i + 1;
                while (j < l && nums[j] == nums[i]) {
                    nums[j] = 10001;
                    ++j;
                }
                i = j;
            } else {
                i++;
            }
        }
        sort(nums);
        for (int i = l - 1; i >= 0; i--) {
            if (nums[i] != 10001)
                break;
            ++res;
        }
        return l - res;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) return;
        int a = array[i];
        array[i] = array[j];
        array[j] = a;
    }

    private static int partition(int[] ns, int begin, int end) {
        int pivotIndex = (begin + end) / 2;
        int pivot = ns[pivotIndex];
        swap(ns, pivotIndex, end);
        int index = begin;
        for (int i = begin; i < end; i++) {
            if (ns[i] < pivot) {
                swap(ns, index++, i);
            }
        }
        swap(ns, index, end);
        return index;
    }

    private static void sort(int[] ns, int begin, int end) {
        if (begin >= end) return;
        int index = partition(ns, begin, end);
        sort(ns, begin, index - 1);
        sort(ns, index + 1, end);
    }

    public static void sort(int[] ns) {
        sort(ns, 0, ns.length - 1);
    }

    public static void main(String[] args) {
        int[] data = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int l = removeDuplicates2(data);
        for (int i = 0; i < l; i++) {
            System.out.println(data[i]);
        }
    }
    /*    执行用时：1 ms, 在所有 Java 提交中击败了80.95%的用户
          内存消耗：38.3 MB, 在所有 Java 提交中击败了95.45%的用户      */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ) {
            if (i < len - 1 && nums[i] == nums[i + 1]) {
                int j = i + 2;
                while (j < len && nums[j] == nums[i]) {
                    nums[j] = 10001;
                    ++j;
                }
                i = j;
            } else {
                i++;
            }
        }
        int l = 0, r = 0;
        while (l < len && nums[l] != 10001)
            ++l;
        r = l + 1;
        while (r < len && l < len) {
            while (r < len && nums[r] == 10001)
                ++r;
            if (r < len)
                swap(nums, l, r);
            while (l < len && nums[l] != 10001)
                ++l;
        }
        return l;
    }

    //绝了
    public static int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}