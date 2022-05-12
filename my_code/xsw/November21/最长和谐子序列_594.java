package xsw.November21;
/*594. 最长和谐子序列
和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。

现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。

数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。



示例 1：

输入：nums = [1,3,2,2,5,2,3,7]
输出：5
解释：最长的和谐子序列是 [3,2,2,2,3]
示例 2：

输入：nums = [1,2,3,4]
输出：2
示例 3：

输入：nums = [1,1,1,1]
输出：0


提示：

1 <= nums.length <= 2 * 104
-109 <= nums[i] <= 109
通过次数34,239提交次数64,376

 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 最长和谐子序列_594 {

    public int findLHS1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int k : map.keySet()) {
            int temp = map.get(k);
            int next = 0, pre = 0;
            if (map.containsKey(k - 1)) // 多此一举
                next = map.get(k - 1);
            if (map.containsKey(k + 1))
                pre = map.get(k + 1);
            if (next == 0 && pre == 0)
                continue;
            ans = Math.max(ans, Math.max(temp + next, temp + pre));
        }
        return ans;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int l = 0, r = 0;

        while (r < nums.length) {
            while (nums[r] > nums[l] + 1)
                ++l;
            if (nums[r] == nums[l]) {
                ans = Math.max(ans, r - l + 1);
            }
            ++r;
        }
        return ans;
    }
}