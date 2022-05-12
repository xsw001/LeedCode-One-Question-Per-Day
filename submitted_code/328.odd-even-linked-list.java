//
// @lc app=leetcode.cn id=328 lang=java
//
// [328] odd-even-linked-list
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
    public ListNode oddEvenList(ListNode head) {        
        if (head==null)
            return head;
        ListNode odd = new ListNode();
        ListNode even = new ListNode();
        ListNode temp = new ListNode();
        odd = head;
        even = head.next;
        temp = even;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = even.next;
            even.next = odd.next;
            even = odd.next;
        }
        odd.next = temp;
        return head;
    }
}
// @lc code=end