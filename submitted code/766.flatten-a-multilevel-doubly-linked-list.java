//
// @lc app=leetcode.cn id=766 lang=java
//
// [766] flatten-a-multilevel-doubly-linked-list
//
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {       
         if (head == null) return null;
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = head;
        while (true) {
            if (cur.child != null) {
                // 将 next 节点入栈
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                // 将子链表扁平化
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
            // 遍历子链表的下一个节点或是从栈中弹出 next 节点
            if (cur.next != null) {
                cur = cur.next;
            } else if (!stack.isEmpty()) {
                Node next = stack.pop();
                cur.next = next;
                next.prev = cur;
                cur = next;
            } else {
                return head;
            }
        }
    }
}
// @lc code=end