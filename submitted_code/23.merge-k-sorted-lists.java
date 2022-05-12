//
// @lc app=leetcode.cn id=23 lang=java
//
// [23] merge-k-sorted-lists
//
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || (lists.length == 1 && lists[0] == null))
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if(list != null)
                pq.add(list);
        }
        ListNode node = new ListNode();
        ListNode end = node;
        while ((!pq.isEmpty())) {
            ListNode poll = pq.poll();
            end.next = new ListNode(poll.val);
            end = end.next;
            if (poll.next != null) {
                poll = poll.next;
                pq.add(poll);
            }
        }
        return node.next;
    }
}
// @lc code=end