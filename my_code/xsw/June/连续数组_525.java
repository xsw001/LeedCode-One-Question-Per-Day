package xsw.June;
/*
525. 连续数组
给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。



示例 1:

输入: nums = [0,1]
输出: 2
说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
示例 2:

输入: nums = [0,1,0]
输出: 2
说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。


提示：

1 <= nums.length <= 105
nums[i] 不是 0 就是 1
通过次数14,408提交次数29,671
 */

import java.util.ArrayList;
import java.util.HashMap;

public class 连续数组_525 {

    // 一看就是错的
    public static int findMaxLength1(int[] nums) {
        int zero = 0, one = 0;
        int res = 0;
        for (int num : nums) {
            if (num == 1)
                ++one;
            else
                ++zero;
            if (one == zero)
                res = zero + one;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {0,0,1,0,0,0,1,1};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(findMaxLength(data));
    }

    // 和523 一样
    public static int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                nums[i] = -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            }else
                map.put(sum,i);
        }
        return  res;
    }
}