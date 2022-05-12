package xsw.Nov_Dec;/*  Hard
164. 最大间距
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例 1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
示例 2:

输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。
说明:

你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
*/

import java.util.Arrays;

public class LeedCode164 {

    public static int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length-1; i++) {
            res = Math.max(nums[1+i]-nums[i],res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3,6,9,1};
        int i = maximumGap(arr);
        System.out.println(i);
    }

    //基数排序
    public static int maximumGap2(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        int max = 0;
        for(int i:nums){
            max = Math.max(i,max);
        }
        sort(nums,String.valueOf(max).length());
        int res = 0;
        for (int i = 0; i < nums.length-1; i++) {
            res = Math.max(nums[1+i]-nums[i],res);
        }
        return res;
    }
    public static void sort(int[] number, int d) //d表示最大的数有多少位
    {
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][] temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9
        int[] order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        while (m <= d) {
            for (int value : number) {
                int lsd = ((value / n) % 10);
                temp[lsd][order[lsd]] = value;
                order[lsd]++;
            }
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0)
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }
}
