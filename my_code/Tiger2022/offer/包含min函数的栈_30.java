package Tiger2022.offer;
/*
剑指 Offer 30. 包含min函数的栈
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。



示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.


提示：

各函数的调用总次数不超过 20000 次


注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/

通过次数196,698提交次数352,968
 */

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class 包含min函数的栈_30 {

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
            if(!s2.isEmpty() && a == s2.peekFirst())
                s2.pollFirst();
        }

        public int top() {
            return s1.peekFirst();
        }

        public int min() {
            return s2.peekFirst();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.min();
     */

    @Test
    public void test() throws Exception {

    }
}
