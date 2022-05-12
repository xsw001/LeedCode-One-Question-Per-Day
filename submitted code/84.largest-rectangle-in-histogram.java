//
// @lc app=leetcode.cn id=84 lang=java
//
// [84] largest-rectangle-in-histogram
//
class Solution {
    public int largestRectangleArea(int[] heights) {
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
}
// @lc code=end