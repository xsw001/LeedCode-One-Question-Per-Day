package xsw.Nov_Dec;

/*
239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。



示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
示例 3：

输入：nums = [1,-1], k = 1
输出：[1,-1]
示例 4：

输入：nums = [9,11], k = 2
输出：[11]
示例 5：

输入：nums = [4,-2], k = 2
输出：[4]


提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
*/

import java.util.*;

public class LeedCode239 {

    //超时
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (k == 1)
            return nums;
        int[] ans = new int[nums.length - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        int j = 0;
        int before = nums[j];
        for (int i = 0; i <= nums.length; i++) {
            if (i < k)
                pq.offer(nums[i]);
            else {
                ans[j] = pq.peek();
                pq.remove(before);//remove时间复杂度为 O(n)
                if (k >= nums.length)
                    break;
                pq.offer(nums[k]);//offer时间复杂度为 O(log n)
                before = nums[++j];
                ++k;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] window = maxSlidingWindow2(nums, k);
        System.out.println(Arrays.toString(window));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //优先队列中存储二元组 (num,index)，表示元素 num 在数组中的下标为 index。
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            //我们不断地移除堆顶的元素，直到其确实出现在滑动窗口中。此时，堆顶元素就是滑动窗口中的最大值。
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    //双端队列   ----》   优先队列
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        //使用一个队列存储所有还没有被移除的下标。
        //在队列中，这些下标按照从小到大的顺序被存储
        //并且它们在数组 nums 中对应的值是严格单调递减的
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            //最大值可能在滑动窗口左边界的左侧，并且随着窗口向右移动，它永远不可能出现在滑动窗口中了
            //因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
