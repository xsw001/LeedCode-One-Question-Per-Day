//
// @lc app=leetcode.cn id=100298 lang=java
//
// [100298] fan-zhuan-lian-biao-lcof
//
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode p = head, q = head.next;
        p.next = null;
        while(q != null){
            ListNode t = q.next;
            q.next = p;
            p = q;
            q = t;
        }
        return p;
    }
}
// @lc code=end