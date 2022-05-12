package xsw.March;
/*
503. 下一个更大元素 II
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素
数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数
这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
[1,2,1,3, 5,2]
[2,3,3,5,-1,3]
 */

import java.util.Arrays;
import java.util.Stack;

public class medium_503 {

    //执行用时:75 ms,
    //在所有Java提交中击败了6.32%的用户
    public static int[] nextGreaterElements1(int[] nums) {
        int l = nums.length;
        int i = 0;
        int[] res = new int[l];
        while (i < l) {
            int loc = i;
            int count = 0;
            while (count < l) {
                loc++;
                if (loc == l)
                    loc = 0;
                if (nums[loc] > nums[i])
                    break;
                ++count;
            }
            res[i] = loc == i ? -1 : nums[loc];
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {2, 1, 2, 6, 6, 5};
        System.out.println(Arrays.toString(nextGreaterElements(data)));
    }

    //单调栈
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()])
                res[stack.pop()] = nums[i % n];
            stack.push(i % n);
        }
        return res;
    }
}