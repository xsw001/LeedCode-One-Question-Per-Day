package xsw.Nov_Dec;/*
321. 拼接最大数
给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。

求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。

说明: 请尽可能地优化你算法的时间和空间复杂度。

示例 1:

输入:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
输出:
[9, 8, 6, 5, 3]
示例 2:

输入:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
输出:
[6, 7, 6, 0, 4]
示例 3:

输入:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
输出:
[9, 8, 9]
*/

import java.util.Arrays;
import java.util.LinkedList;

public class LeedCode321单调栈_不会 {

    //老子不会
    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        if (nums1.length + nums2.length == k) {
            int i = 0, j = 0, z = 0;
            while (i < nums1.length || j < nums2.length) {
                if (i < nums1.length && nums1[i] > nums2[j])
                    result[z++] = nums1[i++];
                else
                    result[z++] = nums2[j++];
            }
            return result;
        }
        int index = 0;
        int flag = 0;
        int historyIndex1 = 0;
        int historyIndex2 = 0;
        int[] n1 = findMax(nums1, 0);
        int[] n2 = findMax(nums2, 0);
        int i = 0;
        int len = k;
        for (; i < k; i++) {
            if (n1[0] > n2[0]) {
                result[i] = n1[0];
                index = n1[1] + 1;
                historyIndex1 = index;
                ++len;
                if (index >= nums1.length) {
                    break;
                }
                n1 = findMax(nums1, index);
            } else {
                result[i] = n2[0];
                index = n2[1] + 1;
                historyIndex2 = index;
                ++len;
                if (index >= nums2.length) {
                    flag = 1;
                    break;
                }
                n2 = findMax(nums2, index);
            }
        }
        ++i;
        if (flag == 0) {
            //n2
            for (int a = historyIndex2; a < nums2.length; ++a) {
                result[i] = nums2[a];
                ++i;
            }
        } else {
            //n1
            for (int a = historyIndex1; a < nums1.length; ++a) {
                result[i] = nums1[a];
                ++i;
            }
        }

        return result;
    }

    public static int[] findMax(int[] nums, int index) {
        int maxNumber1 = 0;
        int maxLoc1 = 0;
        for (int i = index; i < nums.length; i++) {
            if (maxNumber1 < nums[i]) {
                maxNumber1 = nums[i];
                maxLoc1 = i;
            }
        }
        return new int[]{maxNumber1, maxLoc1};
    }

    public static void main(String[] args) {
        int[] nums1 = {6, 7};
        int[] nums2 = {6, 0, 4};
        int k = 5;
        int[] ints = maxNumber(nums1, nums2, k);
        System.out.println(Arrays.toString(ints));
        MaxNumber number = new MaxNumber();
        System.out.println(Arrays.toString(number.maxK(nums2, 2)));
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    public static int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int num : nums) {
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public static int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public static int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    /*----------------------------------------------2021-12-18 再刷-----步骤真多啊------------------------------------------*/
    static class MaxNumber {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;
            int[] ans = new int[k];
            String max = "";
            int start = Math.max(0, k - n), end = Math.min(k, m);
            for (int i = start; i <= end; i++) {
                int[] sub1 = maxK(nums1, i);
                int[] sub2 = maxK(nums2, k - i);
                String temp = combine(sub1, sub2);
                if (temp.length() >= max.length())
                    max = maxSting(max, temp);
            }
            for (int i = 0; i < max.length(); i++) {
                ans[i] = max.charAt(i) - '0';
            }
            return ans;
        }

        private String maxSting(String max, String temp) {
            if (temp.length() > max.length())
                return temp;
            int l = max.length();
            for (int i = 0; i < l; i++) {
                if (temp.charAt(i) > max.charAt(i))
                    return temp;
                else if (temp.charAt(i) < max.charAt(i))
                    return max;
            }
            return max;
        }

        private String combine(int[] a, int[] b) {
            int la = a.length, lb = b.length;
            int i = 0, j = 0;
            StringBuilder sb = new StringBuilder();
            while (i < la && j < lb) {
                if (compare2(a, i, b, j) > 0) {
                    sb.append(a[i++]);
                } else
                    sb.append(b[j++]);
            }
            while (i < la)
                sb.append(a[i++]);
            while (j < lb)
                sb.append(b[j++]);
            // return Integer.parseInt(sb.toString());   整数溢出
            return sb.toString();
        }

        // 求数组nums中k个数 组成的最大值
        private int[] maxK(int[] nums, int k) {

            LinkedList<Integer> list = new LinkedList<>();
            int l = nums.length - k;
            for (int num : nums) {
                while (l > 0 && !list.isEmpty() && num > list.peekLast()) {
                    list.pollLast();
                    --l;
                }
                list.addLast(num);
            }
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) {
                ans[i] = list.pollFirst();
            }
            return ans;
        }

        public static int compare2(int[] subsequence1, int index1, int[] subsequence2, int index2) {
            int x = subsequence1.length, y = subsequence2.length;
            while (index1 < x && index2 < y) {
                int difference = subsequence1[index1] - subsequence2[index2];
                if (difference != 0) {
                    return difference;
                }
                index1++;
                index2++;
            }
            return (x - index1) - (y - index2);
        }
    }
}
