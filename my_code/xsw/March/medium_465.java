package xsw.March;
/*
456. 132模式
给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。

注意：n 的值小于15000。

示例1:

输入: [1, 2, 3, 4]

输出: False

解释: 序列中不存在132模式的子序列。
示例 2:

输入: [3, 1, 4, 2]

输出: True

解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
示例 3:

输入: [-1, 3, 2, 0]

输出: True

解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
通过次数17,758提交次数59,207
 */

import java.util.*;

public class medium_465 {

    //思路错误     1,3,2,4,5,6,7,8,9,10
    public static boolean find132pattern1(int[] nums) {
        int l = nums.length;
        int max = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int num : nums)
            if (num > max)
                max = num;
        for (int i = 0; i < l; i++) {
            if (nums[i] == max)
                list.add(i);
        }
        for (Integer i : list) {
            if (i == 0 && i == l - 1)
                continue;
            int two = i + 1;
            while (two < l) {
                int one = i - 1;
                while (one >= 0) {
                    if (nums[one] < nums[two] && nums[two] < nums[i])
                        return true;
                    --one;
                }
                ++two;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] data = {1, 3, 2, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(find132pattern3(data));
    }

    //O(n*n*n)    超时
    public static boolean find132pattern2(int[] nums) {
        int l = nums.length;
        if (l < 3)
            return false;
        for (int one = 0; one < l; one++) {
            for (int three = one + 1; three < l; three++) {
                for (int two = three + 1; two < l; two++) {
                    if (nums[one] < nums[two] && nums[two] < nums[three])
                        return true;
                }
            }
        }
        return false;
    }

    //TreeMap排序的Map   枚举 3
    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            leftMin = Math.min(leftMin, nums[j]);
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }
        return false;
    }

    //单调栈   太秒了
    // 枚举 1 （nums[i]）
    public static boolean find132pattern3(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<Integer>();//放的是 3
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;//记录所有可以真正作为 2 的元素的最大值

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }

        return false;
    }
}