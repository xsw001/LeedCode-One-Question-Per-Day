package xsw.Nov_Dec;/*

283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
*/

import java.util.Arrays;

public class LeedCode283 {
    public static void main(String[] args) {
        int[] nums = {1, 0, 1};
        moveZeroes2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int zero = 0, nzero = 0;
        while (zero < nums.length - 1 && nzero < nums.length - 1) {
            while (nums[zero] != 0) {
                ++zero;
                if (zero == nums.length)
                    return;
            }
            nzero = zero + 1;
            if (nzero == nums.length)
                return;
            while (nums[nzero] == 0) {
                ++nzero;
                if (nzero == nums.length)
                    return;
            }
            int temp = nums[zero];
            nums[zero] = nums[nzero];
            nums[nzero] = temp;
        }
    }

    public static void moveZeroes1(int[] nums) {
        int notZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[notZero++] = nums[i];
        }
        while (notZero < nums.length)
            nums[notZero++] = 0;
    }

    public static void moveZeroes2(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                if (i == j) {
                    ++j;
                    continue;
                }
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

}
