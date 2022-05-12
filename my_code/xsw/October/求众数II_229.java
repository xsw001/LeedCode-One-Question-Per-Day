package xsw.October;
/*
229. 求众数 II
给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。





示例 1：

输入：[3,2,3]
输出：[3]
示例 2：

输入：nums = [1]
输出：[1]
示例 3：

输入：[1,1,1,3,3,2,2,2]
输出：[1,2]


提示：

1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109


进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。

通过次数40,543提交次数85,157
 */

import java.util.*;

public class 求众数II_229 {

    public List<Integer> majorityElement1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        int l = nums.length / 3;
        for (int num : nums) {
            Integer cc = map.getOrDefault(num, 0);
            if (cc == l) {
                list.add(num);
                cc = Integer.MIN_VALUE;
            } else
                ++cc;
            map.put(num, cc);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] data = {2, 1, 1, 3, 1, 4, 5, 6};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(majorityElement(data));
    }

    public static List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int l = nums.length;
        int one = 0, two = 0;
        int v1 = 0, v2 = 0;
        for (int num : nums) {
            if (num == one) {
                ++v1;
            } else if (num == two) {
                ++v2;
            } else if (v1 == 0) {
                one = num;
                ++v1;
            } else if (v2 == 0) {
                two = num;
                ++v2;
            } else {
                --v1;
                --v2;
            }
        }
        v1 = 0;
        v2 = 0;
        for (int num : nums) {
            if (num == one) ++v1;
            else if (num == two) ++v2;
        }
        if (v1 > l / 3) list.add(one);
        if (v2 > l / 3) list.add(two);
        return list;
    }
}