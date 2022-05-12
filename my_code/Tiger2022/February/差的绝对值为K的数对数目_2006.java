package Tiger2022.February;
/*
2006. 差的绝对值为 K 的数对数目
给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。

|x| 的值定义为：

如果 x >= 0 ，那么值为 x 。
如果 x < 0 ，那么值为 -x 。


示例 1：

输入：nums = [1,2,2,1], k = 1
输出：4
解释：差的绝对值为 1 的数对为：
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
示例 2：

输入：nums = [1,3], k = 3
输出：0
解释：没有任何数对差的绝对值为 3 。
示例 3：

输入：nums = [3,2,1,5,4], k = 2
输出：3
解释：差的绝对值为 2 的数对为：
- [3,2,1,5,4]
- [3,2,1,5,4]
- [3,2,1,5,4]


提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100
1 <= k <= 99
通过次数30,049提交次数34,994
请问您在哪类招聘中遇到此题？
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class 差的绝对值为K的数对数目_2006 {

    public int countKDifference1(int[] nums, int k) {
        int ans = 0;
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (Math.abs(nums[i] - nums[j]) == k)
                    ++ans;
            }
        }
        return ans;
    }

    @Test
    public void test() throws Exception {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};

        List<Integer> list = new ArrayList<>();


    }

    public int countKDifference(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int l = nums.length;
        int ans = 0;
        for (int i = l - 1; i >= 0; i--) {
            ans += map.getOrDefault(nums[i] - k, 0);
            ans += map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ans;
    }
}
