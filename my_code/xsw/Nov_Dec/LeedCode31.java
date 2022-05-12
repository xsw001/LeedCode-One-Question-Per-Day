package xsw.Nov_Dec;/*

31. 下一个排列
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

import java.util.Arrays;

public class LeedCode31 {
    public static void nextPermutation(int[] nums) {
        int min = 0;
        int max = 0;
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                min = i - 1;
                break;
            }
        }
        if (i > 0) {
            System.out.println(min + " " + max);
            for (int j = nums.length - 1; j > min; j--) {
                if (nums[j] > nums[min]) {
                    max = j;
                    break;
                }
            }
            System.out.println(min + " " + max);
            swap(nums, min, max);
            System.out.println(Arrays.toString(nums));
            reverse(nums, min + 1);
        }else
            reverse(nums,min);
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int nums[], int start) {
        int end = nums.length - 1;
        System.out.println(end + " " + start);
        while (end > start) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
