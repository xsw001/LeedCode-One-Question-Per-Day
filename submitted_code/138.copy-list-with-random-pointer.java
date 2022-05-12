//
// @lc app=leetcode.cn id=138 lang=java
//
// [138] copy-list-with-random-pointer
//
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        Node dummy = new Node(0);
        Node cur = dummy;
        HashMap<Node, Node> map = new HashMap<>();
        Node p = head;
        while (head != null) {
            Node temp = new Node(head.val);
            map.put(head, temp);
            cur.next = temp;
            cur = temp;
            head = head.next;
        }
        Node q = dummy.next;
        while (p != null) {
            if (p.random != null)
                q.random = map.get(p.random);
            p = p.next;
            q = q.next;
        }
        return dummy.next;
    }
}
// @lc code=end