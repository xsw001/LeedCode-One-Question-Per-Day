package xsw.Nov_Dec;/*

剑指 Offer 40. 最小的k个数
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。



示例 1：

输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
*/

import java.util.Arrays;


public class 剑指offer40迷 {
    /**
     * 我们可以借鉴快速排序的思想。我们知道快排的划分函数每次执行完后都能将数组分成两个部分
     * 小于等于分界值 pivot 的元素的都会被放到数组的左边，大于的都会被放到数组的右边
     * 然后返回分界值的下标。与快速排序不同的是
     * 快速排序会根据分界值的下标递归处理划分的两侧，而这里我们只处理划分的一边。
     * <p>
     * 我们定义函数 randomized_selected(arr, l, r, k) 表示划分数组 arr 的 [l,r] 部分，使前 k 小的数在数组的左侧
     * 在函数里我们调用快排的划分函数，假设划分函数返回的下标是 pos（表示分界值 pivot 最终在数组中的位置）
     * 即 pivot 是数组中第 pos - l + 1 小的数，那么一共会有三种情况：
     * <p>
     * 如果 pos - l + 1 == k，表示 pivot 就是第 kk 小的数，直接返回即可；
     * <p>
     * 如果 pos - l + 1 < k，表示第 kk 小的数在 pivot 的右侧，因此递归调用 randomized_selected(arr, pos + 1, r, k - (pos - l + 1))；
     * <p>
     * 如果 pos - l + 1 > k，表示第 kk 小的数在 pivot 的左侧，递归调用 randomized_selected(arr, l, pos - 1, k)。
     * <p>
     * 函数递归入口为 randomized_selected(arr, 0, arr.length - 1, k)。在函数返回后，将前 k 个数放入答案数组返回即可。
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] result = new int[k];
        if (k >= 0)
            System.arraycopy(arr, 0, result, 0, k);
        return result;
    }

    private static void randomizedSelected(int[] arr, int l, int r, int k) {
        if (l >= r)
            return;
        int pos = partition(arr, l, r);
        //int pos = randomizedPartition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }
/*
没懂这串的意思
    // 基于随机的划分
    private static int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }
*/

    //partition 隔断
    //pivot 支点
    //小于等于分界值 pivot 的元素的都会被放到数组的左边，大于的都会被放到数组的右边, 然后返回分界值的下标。
    private static int partition(int[] nums, int l, int r) {
        int comp = nums[r];
        int i = l - 1;
        for (int j = l; j < r; ++j) {
            if (nums[j] <= comp) {
                ++i;
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

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 2, 3};
        int k = 2;
        int[] numbers = getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(numbers));
    }
}
