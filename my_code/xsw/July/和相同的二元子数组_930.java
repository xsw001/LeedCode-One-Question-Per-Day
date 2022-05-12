package xsw.July;
/*
930. 和相同的二元子数组
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。

子数组 是数组的一段连续部分。



示例 1：

输入：nums = [1,0,1,0,1], goal = 2
输出：4
解释：
如下面黑体所示，有 4 个满足题目要求的子数组：
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
示例 2：

输入：nums = [0,0,0,0,0], goal = 0
输出：15


提示：

1 <= nums.length <= 3 * 104
nums[i] 不是 0 就是 1
0 <= goal <= nums.length
通过次数10,170提交次数21,902
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class 和相同的二元子数组_930 {

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int total = Arrays.stream(nums).sum();
        if (total < goal)
            return 0;
        int ans = 0;
        int size = nums.length;
        int num = 0;
        if (goal == 0) {
            for (int value : nums) {
                if (value == 0)
                    ++num;
                else {
                    ans += num * (num + 1) / 2;
                    num = 0;
                }
            }
            ans += num * (num + 1) / 2;
            return ans;
        }
        int[] lz = new int[size];
        int[] rz = new int[size];
        int zero = 0;
        for (int i = 0; i < size; i++) {
            if (nums[i] == 0)
                ++zero;
            else {
                lz[i] = zero;
                zero = 0;
            }
        }
        zero = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (nums[i] == 0)
                ++zero;
            else {
                rz[i] = zero;
                zero = 0;
            }
        }
        int sum = 0;
        int l = 0, r = 0;
        while (r < size) {
            sum += nums[r];
            if (sum == goal) {
                while (l < size && l < r && nums[l] == 0)
                    ++l;
                ans += (lz[l] + 1) * (rz[r] + 1);
                sum -= nums[l++];
            }
            ++r;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {0, 1, 0, 1, 0, 1};
        System.out.println(numSubarraysWithSum2(data, 2));

    }
/*假设原数组的前缀和数组为 sum，且子数组 (i,j] 的区间和为 goal，
那么 sum[j]−sum[i]=goal。因此我们可以枚举 j ，每次查询满足该等式的 i 的数量。
具体地，我们用哈希表记录每一种前缀和出现的次数，假设我们当前枚举到元素 nums[j]，
我们只需要查询哈希表中元素 sum[j]−goal 的数量即可，
这些元素的数量即对应了以当前 j 值为右边界的满足条件的子数组的数量。
最后这些元素的总数量即为所有和为 goal 的子数组数量。
在实际代码中，我们实时地更新哈希表，以防止出现 i≥j 的情况。
*/
    public static int numSubarraysWithSum1(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }

    public static int numSubarraysWithSum2(int[] nums, int t) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int r = sum[i + 1], l = r - t;
            ans += map.getOrDefault(l, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return ans;
    }
}