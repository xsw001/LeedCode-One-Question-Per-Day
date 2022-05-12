//
// @lc app=leetcode.cn id=100273 lang=java
//
// [100273] yong-liang-ge-zhan-shi-xian-dui-lie-lcof
//
class CQueue {
    Deque<Integer> s1;
    Deque<Integer> s2;
    public CQueue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }
    
    public void appendTail(int value) {
        s1.addFirst(value);
    }
    
    public int deleteHead() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.addFirst(s1.pollFirst());
            }
        }
        return s2.isEmpty() ? -1 : s2.pollFirst();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
// @lc code=end