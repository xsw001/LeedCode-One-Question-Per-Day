package Tiger2022.February;
/*
540. 有序数组中的单一元素
给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。

请你找出并返回只出现一次的那个数。

你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。



示例 1:

输入: nums = [1,1,2,3,3,4,4,8,8]
输出: 2
示例 2:

输入: nums =  [3,3,7,7,10,11,11]
输出: 10


提示:

1 <= nums.length <= 105
0 <= nums[i] <= 105
通过次数54,645提交次数91,254
请问您在哪类招聘中遇到此题？
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 有序数组中的单一元素_540 {

    public int singleNonDuplicates(int[] nums) {
        int len = nums.length;
        int l = 0, r = 0;
        while (r < len) {
            while (r < len && nums[l] == nums[r])
                ++r;
            if (r == l + 1)
                return nums[l];
            l = r;
        }
        return -1;
    }

    @Test
    public void test() throws Exception {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};

        List<Integer> list = new ArrayList<>();


    }

    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (m % 2 == 0) {
                if (nums[m] == nums[m + 1]) // 正常的
                    l = m + 1;
                else
                    r = m;
            } else {
                if (nums[m] == nums[m - 1]) // 正常的
                    l = m + 1;
                else
                    r = m;
            }
        }
        return nums[l];
    }
}
