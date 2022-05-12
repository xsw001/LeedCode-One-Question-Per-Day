package xsw.June.第244场周赛;
/*
5777. 使数组元素相等的减少操作次数 显示英文描述
通过的用户数2512
尝试过的用户数2776
用户总通过次数2550
用户总提交次数4082
题目难度Medium
给你一个整数数组 nums ，你的目标是令 nums 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：

找出 nums 中的 最大 值。记这个值为 largest 并取其下标 i （下标从 0 开始计数）。如果有多个元素都是最大值，则取最小的 i 。
找出 nums 中的 下一个最大 值，这个值 严格小于 largest ，记为 nextLargest 。
将 nums[i] 减少到 nextLargest 。
返回使 nums 中的所有元素相等的操作次数。



示例 1：

输入：nums = [5,1,3]
输出：3
解释：需要 3 次操作使 nums 中的所有元素相等：
1. largest = 5 下标为 0 。nextLargest = 3 。将 nums[0] 减少到 3 。nums = [3,1,3] 。
2. largest = 3 下标为 0 。nextLargest = 1 。将 nums[0] 减少到 1 。nums = [1,1,3] 。
3. largest = 3 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [1,1,1] 。
示例 2：

输入：nums = [1,1,1]
输出：0
解释：nums 中的所有元素已经是相等的。
示例 3：

输入：nums = [1,1,2,2,3]
输出：4
解释：需要 4 次操作使 nums 中的所有元素相等：
1. largest = 3 下标为 4 。nextLargest = 2 。将 nums[4] 减少到 2 。nums = [1,1,2,2,2] 。
2. largest = 2 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [1,1,1,2,2] 。
3. largest = 2 下标为 3 。nextLargest = 1 。将 nums[3] 减少到 1 。nums = [1,1,1,1,2] 。
4. largest = 2 下标为 4 。nextLargest = 1 。将 nums[4] 减少到 1 。nums = [1,1,1,1,1] 。


提示：

1 <= nums.length <= 5 * 104
1 <= nums[i] <= 5 * 104
 */

import java.util.*;

public class LeedCode2 {

    // 超时 40
    public static int reductionOperations1(int[] nums) {
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            PriorityQueue<Integer> queue = map.getOrDefault(nums[i], new PriorityQueue<Integer>());
            queue.offer(i);
            map.put(nums[i], queue);
        }
        int ans = 0;
        while (map.size() != 1) {
            int max = map.firstKey();
            PriorityQueue<Integer> largest = map.remove(max);
            int next = map.firstKey();
            PriorityQueue<Integer> nextLargest = map.remove(next);
            nextLargest.offer(largest.poll());
            if (largest.size() != 0)
                map.put(max, largest);
            map.put(next, nextLargest);
            ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {5, 1, 1, 3, 3, 9,9};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(reductionOperations(data));
        System.out.println(reductionOperations1(data));
        System.out.println(reductionOperations21(data));
    }

    // 超时 41
    public static int reductionOperations2(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer i : map.keySet()) {
            queue.offer(i);
        }
        int res = 0;
        while (queue.size() != 1) {
            Integer largest = queue.poll();
            Integer nextLargest = queue.peek();
            map.put(nextLargest, map.get(nextLargest) + 1);
            if (map.get(largest) > 1) {
                map.put(largest, map.get(largest) - 1);
                queue.offer(largest);
            } else
                map.remove(largest);
            ++res;
        }
        return res;
    }

    public static int reductionOperations21(int[] nums) {
// 统计 nums 每个数出现的次数
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 对 nums 去重， 并且倒序排序
        Set<Integer> keys = new TreeSet<Integer>((x, y) -> {return y - x;});

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            keys.add(num);
        }

        // 计算
        int count = 0, sum = 0;
        for(int key : keys) {
            sum += map.get(key);
            count += sum; // 可以看成 累加 每个最大值的次数，然后最大值变成次大值，一直循环。多加了全是最小值，要减去
        }
        return count - sum;
    }

    // 太厉害了
    /*首先，为了使得 nums 中所有元素相等，我们需要将 nums 中的任意元素都变为 nums 中的最小值。
其次，考虑 nums 中的任意元素 x，每次操作（如有）只能将它变成严格小于它的元素中的最大值。
为了将 x变为 nums 中的最小值，需要的操作次数即为严格小于它的不同值的数量。*/
    public static int reductionOperations(int[] a) {
        Arrays.sort(a);
        int cnt = 0, res = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] != a[i - 1])
                ++cnt;
            res += cnt;
        }
        return res;
    }
}