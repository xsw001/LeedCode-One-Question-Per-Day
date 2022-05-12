//
// @lc app=leetcode.cn id=100300 lang=java
//
// [100300] fu-za-lian-biao-de-fu-zhi-lcof
//
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null)
            return head;
        HashMap<Node,Node> map = new HashMap<>();
        Node dummy = new Node(-1);
        Node p = head,q = dummy;
        while(p != null){
            Node node = new Node(p.val);
            q.next = node;
            q = node;
            map.put(p,node);
            p = p.next;
        }
        p = head;
        q = dummy.next;
        while(p != null){
            q.random = map.get(p.random);
            p = p.next;
            q = q.next;
        }
        return dummy.next;
    }
}
// @lc code=end