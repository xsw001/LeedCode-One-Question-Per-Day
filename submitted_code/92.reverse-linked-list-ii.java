//
// @lc app=leetcode.cn id=92 lang=java
//
// [92] reverse-linked-list-ii
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode behind = cur.next;
        for (int i = left; i < right; i++) {
            cur.next = behind.next;
            behind.next = pre.next;
            pre.next = behind;
            behind = cur.next;
        }
        return dummy.next;
    }
}
// @lc code=end