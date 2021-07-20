//
// @lc app=leetcode.cn id=100301 lang=java
//
// [100301] zui-xiao-de-kge-shu-lcof
//
class Solution {
public static int[] getLeastNumbers(int[] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] result = new int[k];
        if (k >= 0) System.arraycopy(arr, 0, result, 0, k);
        return result;
    }

    private static void randomizedSelected(int[] arr, int l, int r, int k) {
        if (l >= r)
            return;
        int pos = partition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    //partition 隔断
    //pivot 支点
    //小于等于分界值 pivot 的元素的都会被放到数组的左边，大于的都会被放到数组的右边, 然后返回分界值的下标。
    private static int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end