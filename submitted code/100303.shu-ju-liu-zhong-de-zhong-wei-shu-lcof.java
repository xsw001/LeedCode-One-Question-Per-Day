//
// @lc app=leetcode.cn id=100303 lang=java
//
// [100303] shu-ju-liu-zhong-de-zhong-wei-shu-lcof
//
    class MedianFinder {
        PriorityQueue<Integer> max;
        PriorityQueue<Integer> min;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            max = new PriorityQueue<>(Comparator.comparingInt(o -> o));
            min = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        public void addNum(int num) {
            if (min.isEmpty() || num <= min.peek()) {
                min.add(num);
                if (min.size() > max.size() + 1)
                    max.add(min.poll());
            } else {
                max.add(num);
                if (max.size() > min.size())
                    min.add(max.poll());
            }
        }

        public double findMedian() {
            if(max.size() == min.size())
                return (min.peek() + max.peek())/2.0;
            return min.peek();
        }
    }
// @lc code=end