package xsw.March;
/*
84. 柱状图中最大的矩形
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。





以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。





图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。



示例:

输入: [2,1,5,6,2,3]
输出: 10
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class hard_94 {

    public static int largestRectangleArea(int[] heights) {
        int l = heights.length,res = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int[] left = new int[l];
        int[] right = new int[l];
        stack.add(-1);
        for (int i = 0; i < l; i++) {
            if(stack.peekLast() == -1 || heights[i] > heights[stack.peekLast()]) {
                left[i] = stack.peekLast();
                stack.addLast(i);
            }
            else{
                while(heights[i] <= heights[stack.peekLast()]) {
                    stack.pollLast();
                    if(stack.peekLast() == -1)
                        break;
                }
                left[i] = stack.peekLast();
                stack.addLast(i);
            }
        }
        stack.clear();
        stack.add(l);
        for (int i = l-1; i >= 0; i--) {
            if(stack.peekLast() == l || heights[i] > heights[stack.peekLast()]) {
                right[i] = stack.peekLast();
                stack.addLast(i);
            }
            else{
                while(heights[i] <= heights[stack.peekLast()]) {
                    stack.pollLast();
                    if(stack.peekLast() == l)
                        break;
                }
                right[i] = stack.peekLast();
                stack.addLast(i);
            }
        }
        for (int i = 0; i < l; i++) {
            res = Math.max(res,heights[i]*(right[i] - left[i] - 1));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {6,7,5,2,4,5,9,3};
        System.out.println(largestRectangleArea(data));
    }

    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}