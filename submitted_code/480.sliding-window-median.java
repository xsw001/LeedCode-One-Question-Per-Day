//
// @lc app=leetcode.cn id=480 lang=java
//
// [480] sliding-window-median
//
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int l = nums.length;
        int[] window = new int[k];
        double[] res = new double[l - k + 1];
        if (k >= 0)
            System.arraycopy(nums, 0, window, 0, k);
        int index = 0;
        for (int i = k; i <= l; i++) {
            MedianFinder obj = new MedianFinder(k);
            for (int in : window) {
                obj.addNum(in);
            }
            double median = obj.findMedian();
            res[i - k] = median;
            int num = nums[index++];
            for (int j = 0; j < k; j++) {
                if (i < l && num == window[j]) {
                    window[j] = nums[i];
                    break;
                }
            }
        }
        return res;
    }

    class MedianFinder {

        private int count;
        private int size;
        private PriorityQueue<Integer> maxheap;
        private PriorityQueue<Integer> minheap;

        public MedianFinder(int k) {
            count = 0;
            size = k;
            this.minheap = new PriorityQueue<Integer>(new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                    return num2.compareTo(num1);
                }
            });
            this.maxheap = new PriorityQueue<Integer>(new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                    return num1.compareTo(num2);
                }
            });
        }

        public void addNum(int num) {
            count += 1;
            maxheap.offer(num);
            minheap.add(maxheap.poll());
            // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
            if ((count & 1) != 0) {
                maxheap.add(minheap.poll());
            }
        }

        public double findMedian() {
            if ((size & 1) == 0) {
                // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
                long a = maxheap.peek();
                long b = minheap.peek();
                return (a + b) / 2.0;
            } else {
                // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
                return (double) maxheap.peek();
            }
        }
    }
}
// @lc code=end