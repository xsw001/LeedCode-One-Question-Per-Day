package Tiger2022.January;
/*
219. 存在重复元素 II
给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。



示例 1：

输入：nums = [1,2,3,1], k = 3
输出：true
示例 2：

输入：nums = [1,0,1,1], k = 1
输出：true
示例 3：

输入：nums = [1,2,3,1,2,3], k = 2
输出：false

提示：

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105
通过次数135,737提交次数313,717
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 存在重复元素2_219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0)
            return true;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i <= k || i < nums.length; i++) {
            set.add(nums[i]);
        }
        if (k >= nums.length)
            return set.size() != nums.length;
        int l = 0, r = k + 1;
        while (r < nums.length) {
            if (set.size() != k + 1)
                return true;
            set.remove(nums[l++]);
            set.add(nums[r++]);
        }
        return set.size() != k + 1;
    }

    @Test
    public void test() {
        int[] data = {1, 0, 1, 1};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(containsNearbyDuplicate(data, 1));
    }

    class Solution {
        // 方法一样，但是简单
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> set = new HashSet<Integer>();
            int length = nums.length;
            for (int i = 0; i < length; i++) {
                if (i > k) {
                    set.remove(nums[i - k - 1]);
                }
                if (!set.add(nums[i])) {
                    return true;
                }
            }
            return false;
        }
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/contains-duplicate-ii/solution/cun-zai-zhong-fu-yuan-su-ii-by-leetcode-kluvk/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}