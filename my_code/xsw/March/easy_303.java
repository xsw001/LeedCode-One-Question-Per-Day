package xsw.March;
/*
给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。

实现 NumArray 类：

NumArray(int[] nums) 使用数组 nums 初始化对象
int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 

示例：

输入：
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
输出：
[null, 1, -1, -3]

解释：
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 

提示：

0 <= nums.length <= 104
-105 <= nums[i] <= 105
0 <= i <= j < nums.length
最多调用 104 次 sumRange 方法
 */

public class easy_303 {


    public static void main(String[] args) {
        int[] arr = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }

    static class NumArray {

        int[] nums;
        int total = 0;

        public NumArray(int[] nums) {
            this.nums = nums;
            for (int i : nums) {
                total += i;
            }
        }

        public int sumRange(int i, int j) {
            int res = 0;
            if (j - i + 1 <= nums.length / 2) {
                while (i <= j) {
                    res += nums[i++];
                }
                return res;
            } else {
                for (int a = 0; a < i; a++) {
                    res += nums[a];
                }
                for (int b = j+1; b < nums.length; ++b) {
                    res += nums[b];
                }
                return total - res;
            }
        }
        /*
            int[] sums;

            public NumArray(int[] nums) {
                int n = nums.length;
                sums = new int[n + 1];
                for (int i = 0; i < n; i++) {
                    sums[i + 1] = sums[i] + nums[i];
                }
            }

            public int sumRange(int i, int j) {
                return sums[j + 1] - sums[i];
            }
        */
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}