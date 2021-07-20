//
// @lc app=leetcode.cn id=19 lang=java
//
// [19] remove-nth-node-from-end-of-list
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next==null && n==1)
            return null;
        ListNode begin = new ListNode();
        begin = head;
        ListNode end = new ListNode();
        end = head;
        while(n!=0){
            begin = begin.next;
            --n; 
        }
        if(begin==null)
            return head.next;
        while(begin.next!=null){
            begin = begin.next;
            end = end.next;
        }
        begin = end.next;
        end.next = begin.next;
        return head;
    }
}
// @lc code=end