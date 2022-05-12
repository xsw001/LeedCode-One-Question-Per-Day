package xsw.October;
/*
496. 下一个更大元素 I
给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。

请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。



示例 1:

输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
示例 2:

输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。


提示：

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 104
nums1和nums2中所有整数 互不相同
nums1 中的所有整数同样出现在 nums2 中


进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？

通过次数129,253提交次数184,265
 */

import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;

public class 下一个更大元素I_496 {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            int target = nums1[i];
            boolean flag = false;
            int j = 0;
            for (; j < nums2.length; j++) {
                if (nums2[j] == target)
                    flag = true;
                if (flag && nums2[j] > target) {
                    nums1[i] = nums2[j];
                    break;
                }
            }
            if (j == nums2.length)
                nums1[i] = -1;
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 2};
        int[] b = {1, 4, 2, 3};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(Arrays.toString(nextGreaterElement(a, b)));
    }

    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            Deque<Integer> stack = new ArrayDeque<Integer>();
            for (int i = nums2.length - 1; i >= 0; --i) {
                int num = nums2[i];
                while (!stack.isEmpty() && num >= stack.peek()) {
                    stack.pop();
                }
                map.put(num, stack.isEmpty() ? -1 : stack.peek());
                stack.push(num);
            }
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; ++i) {
                res[i] = map.get(nums1[i]);
            }
            return res;
        }
    }

/*    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode-bfcoj/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}