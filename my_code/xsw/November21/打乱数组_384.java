package xsw.November21;
/*
384. 打乱数组
给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。

实现 Solution class:

Solution(int[] nums) 使用整数数组 nums 初始化对象
int[] reset() 重设数组到它的初始状态并返回
int[] shuffle() 返回数组随机打乱后的结果


示例：

输入
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
输出
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

解释
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]


提示：

1 <= nums.length <= 200
-106 <= nums[i] <= 106
nums 中的所有元素都是 唯一的
最多可以调用 5 * 104 次 reset 和 shuffle
通过次数58,878提交次数100,512
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class 打乱数组_384 {

    class Solution {

        int[] original;
        int[] arr;
        ArrayList<Integer> cur;
        int n;

        public Solution(int[] nums) {
            n = nums.length;
            original = Arrays.copyOf(nums, n);
            arr = Arrays.copyOf(nums, n);
            cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
        }

        public int[] reset() {
            return original;
        }

        /*
        数组中的 nums[i .. n−1] 的部分为待乱序的数组，
        其长度为 n-i；nums[0 .. i−1] 的部分为乱序后的数组，其长度为 i。
         */
        public int[] shuffle() {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                int j = i + random.nextInt(n - i);
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
            return arr;
        }

        public int[] shuffle1() {
            Collections.shuffle(cur);
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[i] = cur.get(i);
            }
            return ans;
        }
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}