//
// @lc app=leetcode.cn id=100302 lang=java
//
// [100302] bao-han-minhan-shu-de-zhan-lcof
//
    class MinStack {

        Deque<Integer> s1;
        Deque<Integer> s2;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            s1 = new LinkedList<>();
            s2 = new LinkedList<>();
        }

        public void push(int x) {
            s1.addFirst(x);
            if(s2.isEmpty() || s2.peekFirst() >= x)
                s2.addFirst(x);
        }

        public void pop() {
            int a = s1.pollFirst();
            if(a == s2.peekFirst())
                s2.pollFirst();
        }

        public int top() {
            return s1.peekFirst();
        }

        public int min() {
            return s2.peekFirst();
        }
    }
// @lc code=end